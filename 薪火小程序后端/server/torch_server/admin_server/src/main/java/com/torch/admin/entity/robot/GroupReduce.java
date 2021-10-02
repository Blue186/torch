package com.torch.admin.entity.robot;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("groupreduce")
@Data
public class GroupReduce {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String accountCode;
    private String accountNickname;
    private String accountRemark;
    private String time;
    private String groupCode;

}
