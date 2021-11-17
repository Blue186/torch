package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("thumbs")
public class Thumbs {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer artId;
    private String userName;
}
