package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sign_up")
@Data
public class SignUp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer actChiId;
    private Date createTime;
}
