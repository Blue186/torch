package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 推文信息表
 */

@TableName("article")
@Data
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer authorId;
    private Date createTime;
    private Date updateTime;
    private String content;
    private Integer commentId;//这个可能不需要，应该是在comment表中设置artId
    private Integer thumbsUp;
    private Integer type;
    private Integer views;
}
