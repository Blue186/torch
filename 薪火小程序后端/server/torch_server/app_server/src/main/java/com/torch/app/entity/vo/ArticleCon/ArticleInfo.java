package com.torch.app.entity.vo.ArticleCon;

import com.torch.app.entity.ArtImages;
import com.torch.app.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleInfo extends Article implements Serializable {
    private List<ArtImages> artImages;
}
