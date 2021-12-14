package com.torch.app.entity.vo.ArticleCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class PublishArticle implements Serializable {
    @ApiModelProperty(name = "content",value = "文章内容")
    private String content;
    @ApiModelProperty(name = "static/images",value = "文章图片")
    private String[] imagesUrls;
}
