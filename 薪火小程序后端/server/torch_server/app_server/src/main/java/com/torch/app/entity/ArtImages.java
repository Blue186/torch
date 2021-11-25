package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("art_images")
public class ArtImages {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String url;
    private Integer artId;
}
