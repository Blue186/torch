package com.torch.admin.entity.torch.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TorchUserLogin implements Serializable {

    @ApiModelProperty(value = "登录账号,一般是QQ号,base64格式", name = "account", required = true, example = "记得 base64 字符串")
    private String account;

    @ApiModelProperty(value = "登录密码,base64格式", name = "password", required = true, example = "记得 base64 字符串")
    private String password;

}
