package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Activity;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.User;
import com.torch.app.service.ActivityService;
import com.torch.app.service.SignUpService;
import com.torch.app.service.UserService;
import com.torch.app.util.tools.EmailSendUtil;
import commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/signUp")
public class SignUpController {
    @Resource
    private SignUpService signUpService;
    @Resource
    private ActivityService activityService;
    @Resource
    private UserService userService;
    /**
     * 添加用户报名信息
     * @param signUp 报名信息
     * @return 状态
     */
    @ApiOperation("添加用户报名信息")
    @PostMapping()
    public R<?> sign(@ApiParam(name = "signUp", value = "用户的报名信息", required = true) @RequestBody SignUp signUp){
        EmailSendUtil sendEmail = new EmailSendUtil();
        User user = userService.getBaseMapper().selectById(signUp.getUserId());
        int res = signUpService.getBaseMapper().insert(signUp);
        if (res==1){
//            这里可以添加邮件发送类，发送邮件，后续添加
            sendEmail.simpleEmail("3057179865",user,"薪火志愿报名成功通知","xxxxx报名成功");
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 删除用户的报名信息
     * @param unSignUp 取消报名
     * @return 状态
     */
    @ApiOperation("删除用户的报名信息")
    @DeleteMapping()
    public R<?> unSign(@ApiParam(name = "unSignUp", value = "删除用户的报名", required = true) @RequestBody SignUp unSignUp){
        int res = signUpService.getBaseMapper().deleteById(unSignUp.getId());
        if (res==1){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation("获取用户报名和做过的志愿活动")
    @GetMapping("/{userId}/{current}/{limit}")
    public R<?> getVolInfo(@ApiParam(name = "userId",value = "用户的id",required = true) @PathVariable Integer userId,
            @ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current, 
           @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit){

        QueryWrapper<SignUp> signUpQueryWrapper = new QueryWrapper<>();
        signUpQueryWrapper.eq("userId",userId);
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
    }
}
