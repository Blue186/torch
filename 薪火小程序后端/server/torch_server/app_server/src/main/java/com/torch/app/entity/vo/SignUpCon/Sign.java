package com.torch.app.entity.vo.SignUpCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Sign implements Serializable {
    @ApiModelProperty(name = "actId",value = "活动id")
    private Integer actId;
    @ApiModelProperty(name = "actTimesId",value = "活动时间段id")
    private Integer actTimesId;
    @ApiModelProperty(name = "actChiId",value = "子活动id")
    private Integer actChiId;
}
