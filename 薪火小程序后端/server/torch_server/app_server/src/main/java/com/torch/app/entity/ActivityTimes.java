package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("activity_times")
public class ActivityTimes {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String startTime;
    private String endTime;
    private String address;
    private Integer recruiting;
    private Integer recruited;
    private Integer hours;
    private Integer actChiId;
    private Integer actId;
}
