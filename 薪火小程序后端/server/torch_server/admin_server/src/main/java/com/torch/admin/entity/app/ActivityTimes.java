package com.torch.admin.entity.app;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("activity_times")
@Data
public class ActivityTimes {
    @TableId(type = IdType.AUTO)
    private Integer id; // 子活动时间段表格
    private long startTime; // 开始时间
    private long endTime;   // 结束时间
    private String address; // 地点
    private Integer recruiting; // 需要招募人数
    private Integer recruited;  // 已招募人数
    private float volTime;  // 时长描述，如 1.5 单位小时
    private Integer actChiId;   // 子活动id
    private Integer actId;  // 活动id
}
