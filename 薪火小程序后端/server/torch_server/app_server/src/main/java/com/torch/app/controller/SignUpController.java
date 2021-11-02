package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Activity;
import com.torch.app.entity.ActivityChild;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.User;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 添加用户报名信息
     * @param signUp 报名信息
     * @return 状态
     */
    @ApiOperation("添加用户报名信息")
    @PostMapping()
    public R<?> sign(@ApiParam(name = "signUp", value = "用户的报名信息", required = true) @RequestBody SignUp signUp,
                     HttpServletRequest request){
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            EmailSendUtil sendEmail = new EmailSendUtil();
            User user = userService.getBaseMapper().selectById(uid.toString());
            signUp.setId((Integer) uid);
            int res = signUpService.getBaseMapper().insert(signUp);

            ActivityChild activityChild = activityChildService.getBaseMapper().selectById(signUp.getActChiId());
            Activity activity = activityService.getBaseMapper().selectById(activityChild.getActivityId());
            if (activity.getTotalNumber()<activity.getHeadcount()){
                activity.setTotalNumber(activity.getTotalNumber()+1);//报名实现对应报名人数加一
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("signNum",60);
                return R.error().data(map);
            }

            if (res==1){
//            这里可以添加邮件发送类，发送邮件，后续添加
                sendEmail.simpleEmail("3057179865",user,"薪火志愿报名成功通知","xxxxx报名成功");
                return R.ok();
            }else {
                return R.error().message("未拿到用户信息");
            }
        }else {
            return R.error().code(-100);
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
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            String uid = redisUtil.hmGet(cookie, "uid").toString();

            ActivityChild activityChild = activityChildService.getBaseMapper().selectById(uid);
            Activity activity = activityService.getBaseMapper().selectById(activityChild.getActivityId());
            if (activity.getTotalNumber()>0){
                activity.setTotalNumber(activity.getTotalNumber()-1);//取消报名后，报名人数减一。
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("signNum",61);
                return R.error().data(map);
            }
            int res = signUpService.getBaseMapper().deleteById(uid);
            if (res==1){
                return R.ok();
            }else {
                return R.error().message("未成功删除报名信息");
            }
        }else {
            return R.error().code(-100);
        }

    }

    @ApiOperation("获取用户报名和做过的志愿活动")
    @GetMapping("/{current}/{limit}")
    public R<?> getVolInfo(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
           @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
            HttpServletRequest request){
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            QueryWrapper<SignUp> signUpQueryWrapper = new QueryWrapper<>();
            signUpQueryWrapper.eq("userId",uid);
//        得到了用户参加的所有的志愿活动id
            List<SignUp> signUps = signUpService.getBaseMapper().selectList(signUpQueryWrapper);
            List<Integer> idList = new ArrayList<>();
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
