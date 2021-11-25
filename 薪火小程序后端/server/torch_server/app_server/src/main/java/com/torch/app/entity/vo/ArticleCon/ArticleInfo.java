package com.torch.app.entity.vo.ArticleCon;

import com.torch.app.entity.ArtImages;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleInfo {
    private Integer id;
    private Integer authorId;
    private Date createTime;
    private Date updateTime;
    private String content;
    private Integer commentId;//这个可能不需要，应该是在comment表中设置artId
    private Integer thumbsUp;
    private Integer type;
    private Integer views;
    private List<ArtImages> artImages;
}
