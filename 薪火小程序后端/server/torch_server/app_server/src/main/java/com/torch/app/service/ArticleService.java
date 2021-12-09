package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.Article;
import com.torch.app.entity.vo.ArticleCon.ArticleInfo;
import com.torch.app.entity.vo.ArticleCon.PublishArticle;
import com.torch.app.entity.vo.CommentCon.PublishComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService extends IService<Article> {
    Article setPublishArt(Integer id, PublishArticle publishArt);
    void insertImages(MultipartFile[] images,Integer artId);
    void updateImages(MultipartFile[] images,Integer artId);
    List<ArticleInfo> getArticleInfos(List<Article> records);
    ArticleInfo getArticleInfos(Article article);
}
