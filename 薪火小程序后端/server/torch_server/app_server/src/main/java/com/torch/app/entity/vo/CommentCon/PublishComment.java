package com.torch.app.entity.vo.CommentCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PublishComment implements Serializable {
    @ApiModelProperty(name = "artId",value = "用户文章的id")
    private Integer artId;
    @ApiModelProperty(name = "content",value = "评论内容")
    private String content;
}
