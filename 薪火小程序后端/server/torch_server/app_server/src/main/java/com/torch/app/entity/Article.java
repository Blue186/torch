package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 推文信息表
 */

@TableName("article")
@Data
public class Article implements Serializable {
    @ApiModelProperty(name = "id",value = "文章id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "authorId",value = "(用户)作者id")
    private Integer authorId;
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name = "updateTime",value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(name = "content",value = "文章内容")
    private String content;
    @ApiModelProperty(name = "commentId",value = "评论id")
    private Integer commentId;//这个可能不需要，应该是在comment表中设置artId
    @ApiModelProperty(name = "thumbsUp",value = "点赞数")
    private Integer thumbsUp;
    @ApiModelProperty(name = "type",value = "文章类型，判断来自协会还是个人，1表示个人，二表示协会")
    private Integer type;
    @ApiModelProperty(name = "views",value = "浏览人数")
    private Integer views;
}
