package com.torch.admin.entity.robot;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("entergroup")
@Data
public class EnterGroup {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String accountCode;
    private String accountNickname;
    private String accountAvatar;
    private String accountRemark;
    private String time;
    private String inviterCode;
    private String text;
    private String groupCode;

}
