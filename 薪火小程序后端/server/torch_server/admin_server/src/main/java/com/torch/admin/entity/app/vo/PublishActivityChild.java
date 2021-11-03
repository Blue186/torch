package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PublishActivityChild implements Serializable {

    @ApiModelProperty(value = "所需人数", name = "number")
    private Integer number;

    @ApiModelProperty(value = "志愿时长", name = "volTime")
    private Integer volTime;

    @ApiModelProperty(value = "活动开始时间", name = "startTime")
    private String startTime;

    @ApiModelProperty(value = "活动结束时间", name = "endTime")
    private String endTime;

    @ApiModelProperty(value = "地点", name = "address")
    private String address;

}
