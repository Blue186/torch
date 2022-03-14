package com.torch.app.entity.vo.ActivityChildCon;

import com.torch.app.entity.ActivityTimes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Data
public class GetChild implements Serializable {
    @ApiModelProperty(name = "id",value = "子活动id")
    private Integer id;
    @ApiModelProperty(name = "activity",value = "活动id")
    private Integer activityId;
    @ApiModelProperty(name = "creatTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name = "updateTime",value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(name = "servicePeriod",value = "活动时间段")
    private Long servicePeriod;
    @ApiModelProperty(name = "activityTimes",value = "活动具体时间")
    private List<ActivityTimes> activityTimes;
}
