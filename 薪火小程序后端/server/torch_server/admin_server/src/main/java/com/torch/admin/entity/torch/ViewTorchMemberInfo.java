package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 成员信息视图
 */
@TableName("view_torch_member_info")
@Data
public class ViewTorchMemberInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String accountCode;
    private String birthday;
    private String password;
    private String department;
    private String identifier;
    private String phone;
    private Integer bSee;
    private Integer write;
    private Integer pSee;
    private Integer level;
}
