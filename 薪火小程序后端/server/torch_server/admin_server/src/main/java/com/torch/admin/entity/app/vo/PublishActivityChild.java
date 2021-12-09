package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PublishActivityChild implements Serializable {

    @ApiModelProperty(value = "所需人数", name = "number", example = "所需人数")
    private Integer recruiting;

    @ApiModelProperty(value = "志愿时长", name = "volTime", example = "志愿时长，单位：小时，例如 5.5")
    private float volTime;

    @ApiModelProperty(value = "活动开始时间", name = "startTime", example = "活动开始时间 时间戳")
    private long startTime;

    @ApiModelProperty(value = "活动结束时间", name = "endTime", example = "活动结束时间 时间戳")
    private long endTime;

    @ApiModelProperty(value = "地点", name = "address", example = "地点 短地址")
    private String address;

    @ApiModelProperty(value = "服务具体日期或者日期段", name = "servicePeriod", example = "6月15日")
    private String servicePeriod;

}
