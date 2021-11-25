package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户评论信息表
 */
@TableName("art_comment")
@Data
public class ArtComment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer authorId;
    private Date createTime;
    private Date updateTime;
    private String content;
    private Integer nextId;
    private Integer thumbsUp;
    private Integer artId;
}
