package com.torch.admin.entity.app;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;


/**
 * 子活动信息表
 */
@TableName("activity_child")
@Data
public class ActivityChild {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer activityId;
    private Date createTime;
    private Date updateTime;
    private Integer number;
    private String volTime;
    private String startTime;
    private String endTime;
    private String address;
    private String distinct;
}
