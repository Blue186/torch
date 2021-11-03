package com.torch.admin.controller.app;

import com.torch.admin.entity.app.vo.PublishActivity;
import com.torch.admin.entity.app.vo.PublishActivityChild;
import com.torch.admin.service.app.ActivityChildService;
import com.torch.admin.service.app.ActivityService;
import com.torch.admin.utils.CookieUtils;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = {"志愿活动增删查改统一接口"})
@RestController
@RequestMapping("/app/activity")
public class ActivityController {

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
        // 创建活动
        Integer activityId = activityService.addActivity(publishActivity);
        List<PublishActivityChild> activityChildren = publishActivity.getActivityChildren();
        for (PublishActivityChild child : activityChildren) {
            activityChildService.addActivityChild(child, activityId);
        }
        return R.ok().message("发布成功!");
    }

}
