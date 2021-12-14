package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动信息表
 */
@TableName("activity")
@Data
public class Activity {
    @ApiModelProperty(name = "id",value = "活动id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "identifier",value = "活动编号")
    private String identifier;
    @ApiModelProperty(name = "isPass",value = "活动是否通过，0表示待审核，1表示通过，-1表示不通过")
    private Integer isPass;
    @ApiModelProperty(name = "createrId",value = "活动发起人id")
    private Integer createrId;
    @ApiModelProperty(name = "createTime",value = "活动创建时间")
    private Long createTime;
    @ApiModelProperty(name = "passTime",value = "活动审核通过时间")
    private Long passTime;
    @ApiModelProperty(name = "organizer",value = "自愿组织方")
    private String organizer;
    @ApiModelProperty(name = "headcount",value = "活动所需总人数")
    private Integer headcount;
    @ApiModelProperty(name = "remarks",value = "活动备注")
    private String remarks;
    @ApiModelProperty(name = "totalNumber",value = "已报名人数")
    private Integer totalNumber;
    @ApiModelProperty(name = "attention",value = "活动注意事项")
    private String attention;
    @ApiModelProperty(name = "actImage",value = "活动背景图片")
    private String actImage;
    @ApiModelProperty(name = "qqNumber",value = "活动QQ群")
    private String qqNumber;
    @ApiModelProperty(name = "name",value = "活动名称")
    private String name;
    @ApiModelProperty(name = "address",value = "活动地址")
    private String address;
    @ApiModelProperty(name = "deadline",value = "活动招募截止时间")
    private Long deadline;
    @ApiModelProperty(name = "content",value = "活动内容")
    private String content;
    @ApiModelProperty(name = "volTimeDesc",value = "活动时长描述")
    private String volTimeDesc;
    @ApiModelProperty(name = "volTimePeriod",value = "活动时间范围")
    private String volTimePeriod;
}
