package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("art_images")
public class ArtImages implements Serializable {
    @ApiModelProperty(name = "id",value = "图片id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "url",value = "图片访问路径")
    private String url;
    @ApiModelProperty(name = "artId",value = "对应文章的id")
    private Integer artId;
}
