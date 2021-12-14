package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("impressions")
public class Impressions {
    @ApiModelProperty(name = "id",value = "心得id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "actId",value = "活动id")
    private Integer actId;
    @ApiModelProperty(name = "actChiId",value = "志愿活动子活动id")
    private Integer actChiId;
    @ApiModelProperty(name = "actTimesId",value = "志愿活动子活动时间段id")
    private Integer actTimesId;
    @ApiModelProperty(name = "content",value = "心得内容")
    private String content;
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name = "updateTime",value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(name = "actStars",value = "对志愿活动的评级")
    private Integer actStars;
}
