package com.torch.app.entity.vo.UserCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLogin implements Serializable {

    @ApiModelProperty(value = "微信登录的code，用于获取openid",name = "code")
    private String code;
}
