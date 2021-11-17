package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息表
 */
@TableName("user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String phone;
    private String qq;
    private String email;
    private String school;
    private String grade;
    private Integer isActive;
    private Integer level;
    private String account;
    private String password;
    private String volAccount;
    private String openid;
    private String nickName;
    private String avatarImage;
    private Integer sex;
    private String selfIntro;
}
