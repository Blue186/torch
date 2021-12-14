package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 子活动信息表
 */
@TableName("activity_child")
@Data
public class ActivityChild {
    @ApiModelProperty(name ="id",value = "子活动id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name ="activityId",value = "父活动id")
    private Integer activityId;
    @ApiModelProperty(name ="createTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name ="updateTime",value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(name ="servicePeriod",value = "服务具体日期或者日期段")
    private Long servicePeriod;
}