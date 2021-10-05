package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("torch_member")
@Data
public class TorchMember {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String accountCode;
    private String birthday;
    private String password;
    private String department;
    private String identifier;
    private String phone;

}
