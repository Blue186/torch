package com.torch.app.entity.vo.ArticleCon;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class PublishArticle implements Serializable {
    private String content;
    private MultipartFile[] images;
}
