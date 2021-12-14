package com.torch.admin.entity.app.vo;

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
public class WaitForPassActivityChild {
    private Integer id;
    private long createTime;
    private long updateTime;
    private Integer recruiting;
    private float volTime;
    private long startTime;
    private long endTime;
    private String address;

    public WaitForPassActivityChild() {
    }

    public WaitForPassActivityChild(Integer id, long createTime, long updateTime, Integer recruiting, float volTime, long startTime, long endTime, String address) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.recruiting = recruiting;
        this.volTime = volTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
    }
}
