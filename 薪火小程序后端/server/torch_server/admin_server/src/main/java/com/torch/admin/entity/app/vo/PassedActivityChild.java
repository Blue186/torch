package com.torch.admin.entity.app.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class PassedActivityChild {

    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer number;
    private String volTime;
    private String startTime;
    private String endTime;
    private String address;
    private String distinct;

    public PassedActivityChild() {
    }

    public PassedActivityChild(Integer id, Date createTime, Date updateTime, Integer number, String volTime, String startTime, String endTime, String address, String distinct) {
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
