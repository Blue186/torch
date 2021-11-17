package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublishActivity implements Serializable {

//    @ApiModelProperty(value = "活动编号", name = "identifier", example = "活动编号")
//    private String identifier;

//    @ApiModelProperty(value = "创建人id", name = "createrId", example = "创建人id")
//    private Integer createrId;

    @ApiModelProperty(value = "活动发起方", name = "organizer", example = "活动发起方")
    private String organizer;

//    @ApiModelProperty(value = "活动所需总人数", name = "headcount", example = "活动所需总人数")
//    private Integer headcount;

    @ApiModelProperty(value = "活动备注", name = "remarks", example = "活动备注")
    private String remarks;

    @ApiModelProperty(value = "活动内容说明", name = "content", example = "活动内容说明")
    private String content;

    @ApiModelProperty(value = "活动注意事项", name = "attention", example = "活动注意事项")
    private String attention;

    @ApiModelProperty(value = "活动背景图片", name = "actImage", example = "活动背景图片")
    private String actImage;

    @ApiModelProperty(value = "活动QQ群群号", name = "qqNumber", example = "活动QQ群群号")
    private String qqNumber;

    @ApiModelProperty(value = "子活动时间段list", name = "activityChildren", dataType = "java.util.List<PublishActivityChild>")
    private List<PublishActivityChild> activityChildren;

}
