package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 活动信息表
 */
@TableName("activity")
@Data
public class Activity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String identifier;
    private Integer isPass;
    private Integer createrId;
    private Long createTime;
    private Long passTime;
    private String organizer;
    private Integer headcount;
    private String remarks;
    private Integer totalNumber;
    private String attention;
    private String actImage;
    private String name;
    private String address;
    private Long deadline;
    private String content;
    private String volTimeDesc;
    private String volTimePeriod;
}
