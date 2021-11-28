package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("imp_images")
public class ImpImages {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String url;
    private Integer impId;
}
