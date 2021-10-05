package com.torch.admin.entity.torch.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TorchUser implements Serializable {

    @ApiModelProperty(value = "登录账号,一般是QQ号,base64格式")
    private String account;

    @ApiModelProperty(value = "登录密码,base64格式")
    private String password;

}
