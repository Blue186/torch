package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("thumbs")
public class Thumbs implements Serializable {
    @ApiModelProperty(name = "id",value = "点赞信息id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "actId",value = "文章id")
    private Integer artId;
    @ApiModelProperty(name = "userName",value = "用户名")
    private String userName;
}
