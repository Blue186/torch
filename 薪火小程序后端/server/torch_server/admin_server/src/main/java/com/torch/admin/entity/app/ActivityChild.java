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
    private long createTime;
    private long updateTime;
    private String servicePeriod;   // 服务具体日期或者日期段
}
