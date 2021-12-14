package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("activity_times")
public class ActivityTimes {
    @ApiModelProperty(name ="id",value = "子活动时间段表格")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name ="startTime",value = "开始时间")
    private Long startTime;
    @ApiModelProperty(name ="endTime",value = "结束时间")
    private Long endTime;
    @ApiModelProperty(name ="address",value = "地点")
    private String address;
    @ApiModelProperty(name ="recruiting",value = "需要招募人数")
    private Integer recruiting;
    @ApiModelProperty(name ="recruited",value = "已招募人数")
    private Integer recruited;
    @ApiModelProperty(name ="actChiId",value = "子活动id")
    private Integer actChiId;
    @ApiModelProperty(name ="actId",value = "活动id")
    private Integer actId;
    @ApiModelProperty(name ="volTime",value = "时长描述，如 1.5")
    private  Float volTime;
}
