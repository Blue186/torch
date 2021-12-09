package com.torch.admin.entity.app.vo;

import lombok.Data;


@Data
public class PassedActivityChild {

    private Integer id;
    private long createTime;
    private long updateTime;
    private Integer recruiting;
    private float volTime;
    private long startTime;
    private long endTime;
    private String address;
    private String district;

    public PassedActivityChild() {
    }

    public PassedActivityChild(Integer id, long createTime, long updateTime, Integer recruiting, float volTime, long startTime, long endTime, String address, String district) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.recruiting = recruiting;
        this.volTime = volTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.district = district;
    }
}
