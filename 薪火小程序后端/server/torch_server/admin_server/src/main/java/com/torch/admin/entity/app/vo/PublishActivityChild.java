package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PublishActivityChild implements Serializable {

    @ApiModelProperty(value = "所需人数", name = "number", example = "所需人数")
    private Integer number;

    @ApiModelProperty(value = "志愿时长", name = "volTime", example = "志愿时长")
    private String volTime;

    @ApiModelProperty(value = "活动开始时间", name = "startTime", example = "活动开始时间")
    private String startTime;

    @ApiModelProperty(value = "活动结束时间", name = "endTime", example = "活动结束时间")
    private String endTime;

    @ApiModelProperty(value = "地点", name = "address", example = "地点")
    private String address;

}
