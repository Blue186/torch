package com.torch.admin.controller.app;

import com.torch.admin.entity.app.Activity;
import com.torch.admin.entity.app.ActivityChild;
import com.torch.admin.entity.app.vo.PublishActivity;
import com.torch.admin.entity.app.vo.PublishActivityChild;
import com.torch.admin.entity.app.vo.WaitForPassActivity;
import com.torch.admin.entity.app.vo.WaitForPassActivityChild;
import com.torch.admin.entity.torch.TorchMember;
import com.torch.admin.service.app.ActivityChildService;
import com.torch.admin.service.app.ActivityService;
import com.torch.admin.service.torch.TorchMemberService;
import com.torch.admin.utils.CookieUtils;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Api(tags = {"志愿活动增删查改统一接口"})
@RestController
@RequestMapping("/app/activity")
public class ActivityController {

    @Resource
    private TorchMemberService torchMemberService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityChildService activityChildService;

    @Resource
    private CookieUtils cookieUtils;

    @ApiOperation("发布志愿活动接口")
    @PostMapping("/publish")
    public R<Object> publishActivity(HttpServletRequest request,
                                     @RequestBody(required = true) PublishActivity publishActivity) {
        // 验证用户登录
        Integer uid = cookieUtils.getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").setReLoginData();
        // 验证权限

        // 验证提交内容的完整性

        // 创建活动
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String identifier = String.valueOf(timeInMillis);
        identifier = identifier + String.valueOf(new Double(Math.random() * 100 + 100).intValue());
        List<PublishActivityChild> activityChildren = publishActivity.getActivityChildren();
        int headcount = 0;
        for (PublishActivityChild child : activityChildren) {
            headcount += child.getNumber();
        }
        Integer activityId = activityService.addActivity(publishActivity, uid, identifier, headcount);
        for (PublishActivityChild child : activityChildren) {
            activityChildService.addActivityChild(child, activityId);
        }
        return R.ok().message("发布成功!");
    }

    @ApiOperation("获取待发布志愿活动接口")
    @GetMapping("/getWaitForPass")
    public R<Object> getWaitForPass(HttpServletRequest request) {
        Integer uid = cookieUtils.getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").setReLoginData();
        List<Activity> waitForPassActivities = activityService.getWaitForPass();
        List<WaitForPassActivity> waitForPassActivityList = new ArrayList<>();
        for (Activity activity : waitForPassActivities) {
            TorchMember member = torchMemberService.selectById(activity.getId());
            WaitForPassActivity waitForPassActivity = new WaitForPassActivity(activity.getId(), activity.getIdentifier(),
                    member, activity.getCreateTime(), activity.getOrganizer(), activity.getHeadcount(), activity.getRemarks(),
                    activity.getContent(), activity.getTotalNumber(), activity.getAttention(), activity.getActImage(),
                    activity.getQqNumber());
            List<ActivityChild> activityChildren = activityChildService.getByParentId(activity.getId());
            for (ActivityChild child : activityChildren) {
                WaitForPassActivityChild waitForPassActivityChild = new WaitForPassActivityChild(child.getId(), child.getCreateTime(), child.getUpdateTime(), child.getNumber(),
                        child.getVolTime(), child.getStartTime(), child.getEndTime(), child.getAddress(), child.getDistinct());
                waitForPassActivity.children.add(waitForPassActivityChild);
            }
            waitForPassActivityList.add(waitForPassActivity);
        }
        return R.ok().data(waitForPassActivityList);
    }

    @ApiOperation("改变志愿活动的审核状态")
    @GetMapping("/passActivity/{activityId}")
    public R<Object> passActivity(HttpServletRequest request,
                                  @ApiParam(name = "activityId", value = "活动id", required = true) @PathVariable Integer activityId,
                                  @ApiParam(name = "isPass", value = "1表示通过，-1表示不通过", required = true) @RequestParam Integer isPass) {
        Integer uid = cookieUtils.getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").setReLoginData();
        boolean changePass = activityService.changePass(activityId, isPass);
        if (changePass) return R.ok().message("操作成功!");
        return R.error().message("操作失败!");
    }

}
