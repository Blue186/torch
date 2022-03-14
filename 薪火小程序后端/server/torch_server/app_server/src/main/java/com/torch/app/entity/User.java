package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 */
@TableName("user")
@Data
public class User implements Serializable {
    @ApiModelProperty(name = "id",value = "用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "name",value = "用户姓名")
    private String name;
    @ApiModelProperty(name = "phone",value = "手机号")
    private String phone;
    @ApiModelProperty(name = "qq",value = "qq号")
    private String qq;
    @ApiModelProperty(name = "email",value = "用户邮箱")
    private String email;
    @ApiModelProperty(name = "school",value = "学习")
    private String school;
    @ApiModelProperty(name = "grade",value = "年级")
    private String grade;
    @ApiModelProperty(name = "isActive",value = "账户是否激活")
    private Integer isActive;
    @ApiModelProperty(name = "level",value = "用户权限等级")
    private Integer level;
    @ApiModelProperty(name = "account",value = "薪火账号")
    private String account;
    @ApiModelProperty(name = "password",value = "登录密码")
    private String password;
    @ApiModelProperty(name = "volAccount",value = "志愿者账号")
    private String volAccount;
    @ApiModelProperty(name = "openid",value = "微信openid")
    private String openid;
    @ApiModelProperty(name = "nickName",value = "用户昵称（账号名）")
    private String nickName;
    @ApiModelProperty(name = "avatarImage",value = "用户微信头像")
    private String avatarImage;
    @ApiModelProperty(name = "sex",value = "用户性别")
    private Integer sex;
    @ApiModelProperty(name = "selfIntro",value = "自我介绍")
    private String selfIntro;
    @ApiModelProperty(name = "totalTime",value = "（在薪火获得的）志愿总时长")
    private Float totalTime;
}
