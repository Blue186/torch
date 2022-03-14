package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("contact_us")
public class ContactUs implements Serializable {
    @ApiModelProperty(name = "id",value = "(联系我们)的信息的id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "content",value = "信息内容")
    private String content;
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name = "createTime",value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(name = "resContent",value = "协会恢复信息")
    private String resContent;
    @ApiModelProperty(name = "resTime",value = "回复时间")
    private Long resTime;
}
