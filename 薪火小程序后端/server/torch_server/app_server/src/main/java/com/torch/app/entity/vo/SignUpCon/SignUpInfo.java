package com.torch.app.entity.vo.SignUpCon;

import com.torch.app.entity.SignUp;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpInfo extends SignUp implements Serializable {
//    来自activityTimes
    @ApiModelProperty(name ="volTime",value = "时长描述，如 1.5")
    private  Float volTime;
//    来自activity
    @ApiModelProperty(name = "name",value = "活动名称")
    private String name;
//    来自activityChild
    @ApiModelProperty(name ="servicePeriod",value = "服务具体日期")
    private Long servicePeriod;
}
