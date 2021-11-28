package com.torch.app.entity.vo.UserCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户的姓名",name = "name")
    private String name;
    @ApiModelProperty(value = "用户的电话",name = "phone")
    private String phone;
    @ApiModelProperty(value = "用户的qq",name = "qq")
    private String qq;
    @ApiModelProperty(value = "用户的学校",name = "school")
    private String school;
    @ApiModelProperty(value = "用户的年级",name = "grade")
    private String grade;
    @ApiModelProperty(value = "用户邮箱",name = "email")
    private String email;
    @ApiModelProperty(value = "用户的志愿账号",name = "volAccount")
    private String volAccount;
    @ApiModelProperty(value = "用户的微信名",name = "nickName")
    private String nickName;
    @ApiModelProperty(value = "用户的微信头像",name = "avatarImage")
    private String avatarImage;
    @ApiModelProperty(value = "用户的自我介绍",name = "selfIntro")
    private String selfIntro;
    @ApiModelProperty(value = "用户的性别",name = "sex")
    private Integer sex;
}
