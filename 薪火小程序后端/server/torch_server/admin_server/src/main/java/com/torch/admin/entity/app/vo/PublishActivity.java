package com.torch.admin.entity.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PublishActivity implements Serializable {

    @ApiModelProperty(value = "活动发起方", name = "organizer", example = "活动发起方，如 道安")
    private String organizer;

    @ApiModelProperty(value = "活动备注", name = "remarks", example = "活动备注，如 注意安全，防疫提醒")
    private String remarks;

    @ApiModelProperty(value = "活动注意事项", name = "attention", example = "活动注意事项，如 注意遵守志愿者行为规范")
    private String attention;

    @ApiModelProperty(value = "活动背景图片", name = "actImage", example = "活动背景图片的url")
    private String actImage;

    @ApiModelProperty(value = "活动QQ群群号", name = "qqNumber", example = "活动QQ群群号")
    private String qqNumber;

    @ApiModelProperty(value = "活动名", name = "name", example = "活动名")
    private String name;

    @ApiModelProperty(value = "活动地点", name = "address", example = "活动地点，如 解放碑")
    private String address;

    @ApiModelProperty(value = "招募截止时间", name = "deadline", example = "招募截止时间，时间戳格式")
    private long deadline;

    @ApiModelProperty(value = "活动内容说明", name = "content", example = "活动内容说明")
    private String content;

    @ApiModelProperty(value = "志愿活动时长描述", name = "volTimeDesc", example = "志愿活动时长描述，如 5.5小时")
    private String volTimeDesc;

    @ApiModelProperty(value = "活动时间范围", name = "volTimePeriod", example = "活动时间范围，如 12月5号-12月6号，格式不固定")
    private String volTimePeriod;

    @ApiModelProperty(value = "子活动时间段list", name = "activityChildren", dataType = "java.util.List<PublishActivityChild>")
    private List<PublishActivityChild> activityChildren;

}
