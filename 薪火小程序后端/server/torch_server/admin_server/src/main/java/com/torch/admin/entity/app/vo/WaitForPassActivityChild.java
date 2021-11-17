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
    private Date createTime;
    private Date updateTime;
    private Integer number;
    private String volTime;
    private String startTime;
    private String endTime;
    private String address;
    private String distinct;

    public WaitForPassActivityChild() {
    }

    public WaitForPassActivityChild(Integer id, Date createTime, Date updateTime, Integer number, String volTime, String startTime, String endTime, String address, String distinct) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.number = number;
        this.volTime = volTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.distinct = distinct;
    }
}
