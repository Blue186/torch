package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        if (judge){
            return R.error().code(-100);
        }
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()>0){
            return R.error().message("资源已被占用请稍后");
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        User user = userService.getBaseMapper().selectById(uid.toString());//拿到用户信息
        if (user.getIsActive()==0){
            Map<String,Object> map = new HashMap<>();
            map.put("register",false);//如果未激活，将直接拒绝此请求。
            return R.error().data(map);
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
            signUp.setCreateTime(new Date());
            int res=0;
            try {
                semaphore.acquire(1);
                res = signUpService.getBaseMapper().insert(signUp);//插入报名信息
                activity.setTotalNumber(activity.getTotalNumber()+1);//报名实现对应报名人数加一
                activityService.getBaseMapper().updateById(activity);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(1);
            }
            if (res==1){
                EmailSendUtil sendEmail = new EmailSendUtil();
//            这里可以添加邮件发送类，发送邮件，后续添加
                sendEmail.simpleEmail("3057179865",user,"薪火志愿报名成功通知", activity.getName()+"活动报名成功");
                return R.ok();
            }else {
                return R.error().message("未拿到用户信息");
            }
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("signNum",60);//如果报名人数满了，返回signNum
            return R.error().data(map);
        }

    }

    /**
     * 删除用户的报名信息
     * @param unSignUp 取消报名
     * @return 状态
     */
    @ApiOperation("删除用户的报名信息")
    @DeleteMapping()
    public R<?> unSign(@ApiParam(name = "unSignUp", value = "删除用户的报名", required = true) @RequestBody SignUp unSignUp,
                       HttpServletRequest request){
//        验证用户请求是否合法
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
//        检验多线程
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            return R.error().message("线程占用中");
        }
//        单线程则进行如下操作
        String cookie = judgeCookieToken.getCookie(request);
        String uid = redisUtil.hmGet(cookie, "uid").toString();
        ActivityChild activityChild = activityChildService.getBaseMapper().selectById(uid);
        Activity activity = activityService.getBaseMapper().selectById(activityChild.getActivityId());
        int res=0;
        try {
            semaphore.acquire(1);
            if (activity.getTotalNumber()>0){
                activity.setTotalNumber(activity.getTotalNumber()-1);//取消报名后，报名人数减一。
                activityService.getBaseMapper().updateById(activity);
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("signNum",61);
                return R.error().data(map);
            }
            QueryWrapper<SignUp> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",uid);
            wrapper.eq("act_chi_id",activityChild.getId());
            wrapper.eq("act_id",activity.getId());
            res = signUpService.getBaseMapper().delete(wrapper);
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

    @ApiOperation("获取用户做过的志愿活动")
    @GetMapping("/over/{current}/{limit}")
    public R<?> getVolInfoOver(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
           @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
            HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            QueryWrapper<SignUp> signUpQueryWrapper = new QueryWrapper<>();
            signUpQueryWrapper.eq("userId",uid);
            signUpQueryWrapper.eq("is_over",1);
//        得到了用户参加的所有的志愿活动id
            List<SignUp> signUps = signUpService.getBaseMapper().selectList(signUpQueryWrapper);
            List<Integer> idList = new ArrayList<>();
            //获得用户做过的志愿活动的id，添加到list中
            for (SignUp signUp : signUps) {
                idList.add(signUp.getId());
            }
            Page<Activity> page = new Page<>(current, limit);

            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            wrapper.eq("is_pass", 1);
            for (Integer id : idList) {
                wrapper.or().eq("id",id);
            }
            Page<Activity> activityPage = activityService.getBaseMapper().selectPage(page, wrapper);
            return R.ok().data(activityPage);
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation("获取用户报名但还未完成的志愿活动")
    @GetMapping("/{current}/{limit}")
    public R<?> getVolInfo(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                           @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                           HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            QueryWrapper<SignUp> signUpQueryWrapper = new QueryWrapper<>();
            signUpQueryWrapper.eq("userId",uid);
            signUpQueryWrapper.eq("is_over",0);
//        得到了用户参加的所有的志愿活动id
            List<SignUp> signUps = signUpService.getBaseMapper().selectList(signUpQueryWrapper);
            List<Integer> idList = new ArrayList<>();
            //获得用户做过的志愿活动的id，添加到list中
            for (SignUp signUp : signUps) {
                idList.add(signUp.getId());
            }
            Page<Activity> page = new Page<>(current, limit);

            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            wrapper.eq("is_pass", 1);
            for (Integer id : idList) {
                wrapper.or().eq("id",id);
            }
            Page<Activity> activityPage = activityService.getBaseMapper().selectPage(page, wrapper);
            return R.ok().data(activityPage);
        }else {
            return R.error().code(-100);
        }
    }
}
