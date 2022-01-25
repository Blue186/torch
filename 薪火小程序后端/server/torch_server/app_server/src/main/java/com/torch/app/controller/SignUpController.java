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
import com.torch.app.util.tools.EmailSendUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.util.commonutils.R;
import com.torch.app.util.commonutils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.Semaphore;

@Api(tags = {"用户报名志愿活动相关接口"},value = "用户报名志愿活动相关接口")
@RestController
@RequestMapping("/signUp")
public class SignUpController {
    @Resource
    private SignUpService signUpService;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityChildService activityChildService;
    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private JudgeCookieToken judgeCookieToken;
    @Resource
    private EmailSendUtil emailSendUtil;

    @Value("${spring.mail.username}")
    private String MAIL;

    private static final Integer INCREASE_NUMBER = 1;
    /**
     * 添加用户报名信息
     * @param sign 报名信息
     * @return 状态
     */
    @ApiOperation("添加用户报名信息")
    @PostMapping()
    public R<?> sign(@ApiParam(name = "sign", value = "用户的报名信息", required = true) @RequestBody Sign sign,
                     HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            return R.error().message("资源已被占用请稍后");
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        User user = userService.getBaseMapper().selectById(uid.toString());//拿到用户信息
        if (user.getIsActive()==0){
            //如果未激活，将直接拒绝此请求。
            return R.error().setErrorCode(ResultCode.notRegister);
        }
//这里用来判断是否满足报名条件，已报名时间是否存在冲突
//        sign.getActTimesId();//这是现在报名的时间段，包括开始结束
//        应该先拿到用户id，然后通过用户id去拿到已经报名但是未完成的任务，再进行时间匹配
        Boolean satisfy = signUpService.satisfySign((Integer) uid, sign);
        if (!satisfy){
            return R.error().setErrorCode(ResultCode.notSatisfySign);
        }
//----------
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
                activity.setTotalNumber(activity.getTotalNumber()+INCREASE_NUMBER);//报名实现对应报名人数加一
                activityService.getBaseMapper().updateById(activity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(1);
            }
            if (res==1){
//            这里可以添加邮件发送类，发送邮件，后续添加
                emailSendUtil.simpleEmail(MAIL,user,"薪火志愿报名成功通知", "亲爱的志愿者，请注意您参与的"+activity.getName()+"活动报名成功");
                return R.ok();
            }else {
                return R.error().message("未拿到用户信息");
            }
        }else {
            //如果报名人数满了，返回signNum
            return R.error().setErrorCode(ResultCode.fullPeople);
        }
    }

//    @ApiOperation("添加用户报名信息")
//    @PostMapping()
//    public R<?> sign(@ApiParam(name = "sign", value = "用户的报名信息", required = true) @RequestBody Sign sign,Integer uid){
//        Semaphore semaphore = new Semaphore(1);
//        if (semaphore.availablePermits()==0){
//            return R.error().message("资源已被占用请稍后");
//        }
//        User user = userService.getBaseMapper().selectById(uid);//拿到用户信息
//        if (user.getIsActive()==0){
//            //如果未激活，将直接拒绝此请求。
//            return R.error().setErrorCode(ResultCode.notRegister);
//        }
////这里用来判断是否满足报名条件，已报名时间是否存在冲突
////        sign.getActTimesId();//这是现在报名的时间段，包括开始结束
////        应该先拿到用户id，然后通过用户id去拿到已经报名但是未完成的任务，再进行时间匹配
//        Boolean satisfy = signUpService.satisfySign(uid, sign);
//        if (!satisfy){
//            return R.error().setErrorCode(ResultCode.notSatisfySign);
//        }
////----------
//        ActivityChild activityChild = activityChildService.getBaseMapper().selectById(sign.getActChiId());
//        Activity activity = activityService.getBaseMapper().selectById(activityChild.getActivityId());
//        if (activity.getTotalNumber()<activity.getHeadcount()){
//            SignUp signUp = new SignUp();
//            signUp.setActTimesId(sign.getActTimesId());
//            signUp.setActChiId(sign.getActChiId());
//            signUp.setActId(sign.getActId());
//            signUp.setUserId(uid);//设置用户id
//            signUp.setIsOver(0);
//            signUp.setImpWrote(0);
//            signUp.setCreateTime(new Date().getTime());
//            int res=0;
//            try {
//                semaphore.acquire(1);
//                res = signUpService.getBaseMapper().insert(signUp);//插入报名信息
//                activity.setTotalNumber(activity.getTotalNumber()+INCREASE_NUMBER);//报名实现对应报名人数加一
//                activityService.getBaseMapper().updateById(activity);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                semaphore.release(1);
//            }
//            if (res==1){
////            这里可以添加邮件发送类，发送邮件，后续添加
//                emailSendUtil.simpleEmail(MAIL,user,"薪火志愿报名成功通知", "亲爱的志愿者，请注意您参与的"+activity.getName()+"活动报名成功");
//                return R.ok();
//            }else {
//                return R.error().message("未拿到用户信息");
//            }
//        }else {
//            //如果报名人数满了，返回signNum
//            return R.error().setErrorCode(ResultCode.fullPeople);
//        }
//    }

    /**
     * 删除用户的报名信息
     * @param signId 取消报名
     * @return 状态
     */
    @ApiOperation("删除用户的报名信息")
    @DeleteMapping()
    public R<?> unSign(@ApiParam(name = "signId", value = "删除用户的报名", required = true) @RequestBody Integer signId,
                       HttpServletRequest request){
//        验证用户请求是否合法
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().setReLoginData();
        }
//        检验多线程
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            return R.error().message("线程占用中");
        }
//        单线程则进行如下操作

        SignUp signUp = signUpService.getBaseMapper().selectById(signId);
        Activity activity = activityService.getBaseMapper().selectById(signUp.getActId());
        int res=0;
        try {
            semaphore.acquire(1);
            if (activity.getTotalNumber()>0){
                activity.setTotalNumber(activity.getTotalNumber()-INCREASE_NUMBER);//取消报名后，报名人数减一。
                activityService.getBaseMapper().updateById(activity);
            }else {
                return R.error().setErrorCode(ResultCode.noPeople);
            }
            res = signUpService.getBaseMapper().deleteById(signId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(1);
        }
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("未成功删除报名信息");
        }
    }

    @ApiOperation("获取用户志愿活动信息")
    @GetMapping("/signInfo/{done}")
    public R<?> getVolInfoOver(@PathVariable @ApiParam(name = "done",value = "获取做过（1），还未做（0）的志愿报名信息")  Integer done,
            HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge) {
            return R.error().setReLoginData();
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        List<?> signUpInfos = signUpService.getSignUpInfo((Integer) uid,done);
        return R.ok().data(signUpInfos);
    }

//    @ApiOperation("获取用户志愿活动信息")
//    @GetMapping("/signInfo/{done}")
//    public R<?> getVolInfoOver(Integer uid,
//            @PathVariable @ApiParam(name = "done",value = "获取做过（1），还未做（0）的志愿报名信息")  Integer done){
//
//        List<SignUpInfo> signUpInfos = signUpService.getSignUpInfo(uid,done);
//        return R.ok().data(signUpInfos);
//    }


}
