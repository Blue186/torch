package com.torch.admin.entity.robot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("groupmessage")
@Data
public class GroupMessage {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String accountCode;
    private String accountNickname;
    private String accountAvatar;
    private Integer joinTime;
    private Integer anonymous;
    private String accountRemark;
    private String message;
    private String time;
    private String accountTitle;
    private String groupCode;

}
