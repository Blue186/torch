package com.torch.app.entity.vo.ArticleCon;

import com.torch.app.entity.ArtImages;
import com.torch.app.entity.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleInfo extends Article implements Serializable {
    @ApiModelProperty(name = "artImages",value = "文章的图片")
    private List<ArtImages> artImages;
}
