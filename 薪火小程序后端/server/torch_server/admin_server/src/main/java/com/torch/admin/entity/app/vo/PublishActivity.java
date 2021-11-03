package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublishActivity implements Serializable {

    @ApiModelProperty(value = "活动编号", name = "identifier")
    private String identifier;

    @ApiModelProperty(value = "创建人id", name = "createrId")
    private Integer createrId;

    @ApiModelProperty(value = "活动发起方", name = "organizer")
    private String organizer;

    @ApiModelProperty(value = "活动所需总人数", name = "headcount")
    private Integer headcount;

    @ApiModelProperty(value = "活动备注", name = "remarks")
    private String remarks;

    @ApiModelProperty(value = "活动内容说明", name = "content")
    private String content;

    @ApiModelProperty(value = "已报名人数", name = "totalNumber")
    private Integer totalNumber;

    @ApiModelProperty(value = "活动注意事项", name = "attention")
    private String attention;

    @ApiModelProperty(value = "活动背景图片", name = "actImage")
    private String actImage;

    @ApiModelProperty(value = "子活动时间段list", name = "activityChildren")
    private List<PublishActivityChild> activityChildren;

}
