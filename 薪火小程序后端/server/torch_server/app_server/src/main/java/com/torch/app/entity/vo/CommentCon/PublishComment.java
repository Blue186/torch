package com.torch.app.entity.vo.CommentCon;

import lombok.Data;

import java.io.Serializable;

@Data
public class PublishComment implements Serializable {
    private Integer artId;
    private String content;
}
