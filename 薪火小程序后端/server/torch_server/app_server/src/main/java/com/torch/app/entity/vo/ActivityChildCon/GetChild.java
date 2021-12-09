package com.torch.app.entity.vo.ActivityChildCon;

import com.torch.app.entity.ActivityTimes;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
public class GetChild {
    private Integer id;
    private Integer activityId;
    private Date createTime;
    private Date updateTime;
    private String servicePeriod;
    private List<ActivityTimes> activityTimes;
}
