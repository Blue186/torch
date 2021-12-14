package com.torch.app.entity.vo.SignUpCon;

import com.torch.app.entity.Activity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpInfoNot extends Activity implements Serializable {
    //    来自activityTimes
    @ApiModelProperty(name ="volTime",value = "时长描述，如 1.5")
    private  Float volTime;
    //    来自activityChild
    @ApiModelProperty(name ="servicePeriod",value = "服务具体日期或者日期段")
    private Long servicePeriod;
}
