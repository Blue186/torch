package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("activity")
@Data
public class Activity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String identifier;
    private Integer isPass;
    private Integer createrId;
    private Date createTime;
    private Date passTime;
    private String organizer;
    private Integer headcount;
    private String remarks;
    private String content;
    private Integer totalNumber;
    private String attention;
}
