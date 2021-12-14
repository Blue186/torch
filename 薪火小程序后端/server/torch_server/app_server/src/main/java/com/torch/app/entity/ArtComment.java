package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户评论信息表
 */
@TableName("art_comment")
@Data
public class ArtComment {
    @ApiModelProperty(name = "id",value = "用户评论id，文章评论表")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "authorId",value = "用户外键id")
    private Integer authorId;
    @ApiModelProperty(name = "createTime",value = "评论创建时间")
    private Long createTime;
    @ApiModelProperty(name = "updateTime",value = "评论更新时间")
    private Long updateTime;
    @ApiModelProperty(name = "content",value = "评论内容")
    private String content;
    @ApiModelProperty(name = "nextId",value = "下一条评论的id")
    private Integer nextId;
    @ApiModelProperty(name = "thumbsUp",value = "评论点赞数")
    private Integer thumbsUp;
    @ApiModelProperty(name = "artId",value = "对应文章的id")
    private Integer artId;
}
