package com.torch.app.controller;

import com.torch.app.entity.Activity;
import com.torch.app.entity.ActivityChild;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.SignUpCon.Sign;
import com.torch.app.service.ActivityChildService;
import com.torch.app.service.ActivityService;
import com.torch.app.service.SignUpService;
import com.torch.app.service.UserService;
import com.torch.app.util.commonutils.CacheCode;
import com.torch.app.util.tools.EmailSendUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.util.commonutils.R;
import com.torch.app.util.commonutils.ResultCode;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(tags = {"用户报名志愿活动相关接口"},value = "用户报名志愿活动相关接口")
@RestController
@RequestMapping("/signUp")
public class SignUpController {

    private SignUpService signUpService;

    private ActivityService activityService;

    private ActivityChildService activityChildService;

    private UserService userService;

    private RedisUtil redisUtil;

    private JudgeCookieToken judgeCookieToken;

    private EmailSendUtil emailSendUtil;

    private RedissonClient redissonClient;

    @Value("${spring.mail.username}")
    private String MAIL;

    private static final Integer INCREASE_NUMBER = 1;

    @Autowired
    public SignUpController(SignUpService signUpService,
                            ActivityService activityService,
                            ActivityChildService activityChildService,
                            UserService userService,
                            RedisUtil redisUtil,
                            JudgeCookieToken judgeCookieToken,
                            EmailSendUtil emailSendUtil,
                            RedissonClient redissonClient) {
        this.signUpService = signUpService;
        this.activityService = activityService;
        this.activityChildService = activityChildService;
        this.userService = userService;
        this.redisUtil = redisUtil;
        this.judgeCookieToken = judgeCookieToken;
        this.emailSendUtil = emailSendUtil;
        this.redissonClient = redissonClient;
    }

    /**
     * 添加用户报名信息
     * @param sign 报名信息
     * @return 状态
     */
    @LogCostTime
    @ApiOperation("添加用户报名信息")
    @PostMapping()
    public R<?> sign(@ApiParam(name = "sign", value = "用户的报名信息", required = true) @RequestBody Sign sign,
                     HttpServletRequest request){
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            log.info("报名线程被占用");
            return R.error().message("资源已被占用请稍后");
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        User user = userService.getBaseMapper().selectById(uid.toString());//拿到用户信息
        if (user.getIsActive()==0){
            //如果未激活，将直接拒绝此请求。
            log.info("用户未激活，请激活");
            return R.error().setErrorCode(ResultCode.notRegister);
        }
//这里用来判断是否满足报名条件，已报名时间是否存在冲突
//        sign.getActTimesId();//这是现在报名的时间段，包括开始结束
//        应该先拿到用户id，然后通过用户id去拿到已经报名但是未完成的任务，再进行时间匹配
        Boolean satisfy = signUpService.satisfySign((Integer) uid, sign);
        if (!satisfy){
            log.info("用户报名时间段冲突");
            return R.error().setErrorCode(ResultCode.notSatisfySign);
        }
        ActivityChild activityChild = activityChildService.getBaseMapper().selectById(sign.getActChiId());
        Activity activity = activityService.getBaseMapper().selectById(activityChild.getActivityId());
        if (activity.getTotalNumber()<activity.getHeadcount()){
            SignUp signUp = new SignUp();
            signUp.setActTimesId(sign.getActTimesId());
            signUp.setActChiId(sign.getActChiId());
            signUp.setActId(sign.getActId());
            signUp.setUserId((Integer) uid);//设置用户id
            signUp.setIsOver(0);
            signUp.setImpWrote(0);
            signUp.setCreateTime(new Date().getTime());
            int res=0;
            try {
                semaphore.acquire(1);
                res = signUpService.getBaseMapper().insert(signUp);//插入报名信息
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(1);
            }
            if (res==1){
                activity.setTotalNumber(activity.getTotalNumber()+INCREASE_NUMBER);//报名实现对应报名人数加一
                activityService.getBaseMapper().updateById(activity);
                //添加新增报名信息缓存和activity信息
                redissonClient.getBucket(CacheCode.CACHE_SIGN_UP+signUp.getId()).trySet(sign);
                redissonClient.getBucket(CacheCode.CACHE_ACTIVITY+activity.getId()).trySet(activity);
                log.info("报名信息缓存添加成功");
//            这里可以添加邮件发送类，发送邮件，后续添加
                emailSendUtil.simpleEmail(MAIL,user,"薪火志愿报名成功通知", "亲爱的志愿者，请注意您参与的"+activity.getName()+"活动报名成功");
                return R.ok();
            }else {
                log.info("未拿到用户信息");
                return R.error().message("未拿到用户信息");
            }
        }else {
            //如果报名人数满了，返回signNum
            log.info("报名人数已满");
            return R.error().setErrorCode(ResultCode.fullPeople);
        }
    }

    /**
     * 删除用户的报名信息
     * @param signId 取消报名
     * @return 状态
     */
    @LogCostTime
    @ApiOperation("删除用户的报名信息")
    @DeleteMapping()
    public R<?> unSign(@ApiParam(name = "signId", value = "删除用户的报名", required = true) @RequestBody Integer signId){
//        检验多线程
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            log.error("删除报名信息占用中");
            return R.error().message("线程占用中");
        }
//        单线程则进行如下操作
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        SignUp signUp;//如果缓存中存在signUp就直接用
        if (bloomFilter.contains(CacheCode.CACHE_SIGN_UP+signId)){
            log.info("从缓存中拿到报名信息");
            signUp = (SignUp) redissonClient.getBucket(CacheCode.CACHE_SIGN_UP + signId).get();
        }else {
            log.info("从数据库中拿到报名信息");
            signUp = signUpService.getBaseMapper().selectById(signId);
        }
        Activity activity = activityService.getBaseMapper().selectById(signUp.getActId());
        int res=0;
        try {
            semaphore.acquire(1);
            if (activity.getTotalNumber()>0){
                activity.setTotalNumber(activity.getTotalNumber()-INCREASE_NUMBER);//取消报名后，报名人数减一。
                activityService.getBaseMapper().updateById(activity);
                redissonClient.getBucket(CacheCode.CACHE_ACTIVITY+activity.getId()).trySet(activity,CacheCode.ACTIVITY_TIME, TimeUnit.SECONDS);
                log.info("更新志愿活动信息到缓存");
            }else {
                log.error("已经没有人了呀");
                return R.error().setErrorCode(ResultCode.noPeople);
            }
            res = signUpService.getBaseMapper().deleteById(signId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(1);
        }
        if (res==1){
            //删除数据后就缓存
            redissonClient.getBucket(CacheCode.CACHE_SIGN_UP+signUp.getId()).delete();
            log.info("删除对应报名信息的缓存");
            return R.ok();
        }else {
            log.error("未成功删除报名信息");
            return R.error().message("未成功删除报名信息");
        }
    }

    @LogCostTime
    @ApiOperation("获取用户志愿活动信息")
    @GetMapping("/signInfo/{done}")
    public R<?> getVolInfoOver(@PathVariable @ApiParam(name = "done",value = "获取做过（1），还未做（0）的志愿报名信息")  Integer done,
            HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        List<?> signUpInfos = signUpService.getSignUpInfo((Integer) uid,done);
        return R.ok().data(signUpInfos);
    }
}
