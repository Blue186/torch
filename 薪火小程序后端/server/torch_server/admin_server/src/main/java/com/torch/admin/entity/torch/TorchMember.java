package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 成员信息表
 */
@TableName("torch_member")
@Data
public class TorchMember {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String accountCode;
    private String birthday;
    private String password;
    private String position;
    private String department;
    private String identifier;
    private String phone;
    private Integer powerId;
    private Date enterTime;

}
