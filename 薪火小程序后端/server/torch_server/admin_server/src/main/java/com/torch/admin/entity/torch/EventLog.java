package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("event_log")
@Data
public class EventLog {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String event;
    private Date time;

}
