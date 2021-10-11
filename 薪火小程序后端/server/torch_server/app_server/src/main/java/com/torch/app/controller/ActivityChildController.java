package com.torch.app.controller;

import com.torch.app.entity.ActivityChild;
import com.torch.app.service.ActivityChildService;
import commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/activityChild")
public class ActivityChildController {
    @Resource
    private ActivityChildService activityChildService;

    /**
     * 获取志愿详情的子志愿活动
     * @param activityId 父活动id
     * @return 所有的子志愿活动
     */
    @ApiOperation(value = "志愿详情页志愿信息")
    @GetMapping("/{activityId}")
    public R<?> getChild(@ApiParam(name = "activityId", value = "父活动id",required = true) @PathVariable Integer activityId){
        return R.ok().data("content", activityChildService.selectChild(activityId));
    }
}
