package com.torch.app.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户的id",name = "id")
    private Integer id;
    @ApiModelProperty(value = "用户的姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "用户的电话",name = "phone")
    private String phone;
    @ApiModelProperty(value = "用户的qq",name = "id")
    private String qq;
    @ApiModelProperty(value = "用户的email",name = "id")
    private String email;
    @ApiModelProperty(value = "用户的学校",name = "id")
    private String school;
    @ApiModelProperty(value = "用户的年级",name = "id")
    private String grade;
    @ApiModelProperty(value = "用户的志愿账号",name = "id")
    private String volAccount;
}
