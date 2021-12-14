package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("imp_images")
public class ImpImages {
    @ApiModelProperty(name = "id",value = "心得图片id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "url",value = "心得图片访问路径")
    private String url;
    @ApiModelProperty(name = "impId",value = "心得id")
    private Integer impId;
}
