package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Activity;
import com.torch.app.service.ActivityService;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@Api(tags = {"父活动相关接口"},value = "父活动相关接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;

    private RedissonClient redissonClient;

    private static final String CACHE_ACTIVITY = "activity:";


    @Autowired
    public ActivityController(ActivityService activityService,
                              RedissonClient redissonClient) {
        this.activityService = activityService;
        this.redissonClient = redissonClient;
    }

    @LogCostTime
    @ApiOperation(value = "首页获取志愿信息")
    @GetMapping("/index/{current}/{limit}")
    public R<?> getActivities(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                              @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit) {
//        此处不需要缓存，因为使用的mybatis-plus的分页查询，来自数据库，通过缓存不是很好操作。
        Page<Activity> page = new Page<>(current, limit);
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("is_pass", 1);
        activityService.page(page, wrapper);
        List<Activity> records = page.getRecords();
        return R.ok().data(records);
    }
}
