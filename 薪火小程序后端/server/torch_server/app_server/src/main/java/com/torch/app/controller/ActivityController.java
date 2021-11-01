package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Activity;
import com.torch.app.service.ActivityService;
import com.torch.app.util.tools.JudgeCookieToken;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Api(tags = {"父活动相关接口"},value = "父活动相关接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @ApiOperation(value = "首页获取志愿信息")
    @GetMapping("/index/{current}/{limit}")
    public R<?> getActivities(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                              @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                              HttpServletRequest request) {
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Page<Activity> page = new Page<>(current, limit);

            QueryWrapper<Activity> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("create_time");
            wrapper.eq("is_pass", 1);

            activityService.page(page, wrapper);
            List<Activity> records = page.getRecords();

            return R.ok().data(records);
        }else {
            return R.error().code(-100);
        }

    }

}
