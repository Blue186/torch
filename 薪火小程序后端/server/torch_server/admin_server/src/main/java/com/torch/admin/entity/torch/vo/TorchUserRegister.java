package com.torch.admin.entity.torch.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TorchUserRegister implements Serializable {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "登录账号,一般是QQ号")
    private String account;

    @ApiModelProperty(value = "成员生日")
    private String birthday;

    @ApiModelProperty(value = "职位")
    private String department;

    @ApiModelProperty(value = "电话号码")
    private String phone;

}
