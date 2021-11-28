package com.torch.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ArtImages;
import com.torch.app.entity.Article;
import com.torch.app.entity.vo.ArticleCon.ArticleInfo;
import com.torch.app.entity.vo.ArticleCon.PublishArticle;
import com.torch.app.mapper.ArticleMapper;
import com.torch.app.service.ArtImagesService;
import com.torch.app.service.ArticleService;
import com.torch.app.util.tools.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private ArtImagesService artImagesService;
    @Override
    public Article setPublishArt(Integer id, PublishArticle publishArt) {
        Article article = new Article();
        article.setContent(publishArt.getContent());
        article.setAuthorId(id);
        article.setCreateTime(new Date().getTime());
        article.setUpdateTime(new Date().getTime());
        article.setThumbsUp(0);
        article.setViews(0);
        return article;
    }

    @Override
    public void insertImages(MultipartFile[] images, Integer artId) {
        String[] urls = fileUtil.uploadImage(images);
        for (String url : urls) {
            ArtImages artImages = new ArtImages();
            artImages.setUrl(url);
            artImages.setArtId(artId);
            artImagesService.getBaseMapper().insert(artImages);
        }
    }

    @Override
    public void updateImages(MultipartFile[] images, Integer artId) {
        int res = artImagesService.getBaseMapper().deleteById(artId);
        if (res==1){
            String[] urls = fileUtil.uploadImage(images);
            for (String url : urls) {
                ArtImages artImages = new ArtImages();
                artImages.setUrl(url);
                artImages.setArtId(artId);
                artImagesService.getBaseMapper().insert(artImages);
            }
        }
    }

    @Override
    public List<ArticleInfo> getArticleInfos(List<Article> records) {
        List<ArticleInfo> articleInfos = new ArrayList<>();
        for (Article record : records) {
            QueryWrapper<ArtImages> wrapper = new QueryWrapper<>();
            wrapper.eq("art_id", record.getId());
            List<ArtImages> artImages = artImagesService.getBaseMapper().selectList(wrapper);
            ArticleInfo articleInfo = new ArticleInfo();

            articleInfo.setId(record.getId());
            articleInfo.setAuthorId(record.getAuthorId());
            articleInfo.setCreateTime(record.getCreateTime());
            articleInfo.setUpdateTime(record.getUpdateTime());
            articleInfo.setContent(record.getContent());
            articleInfo.setCommentId(record.getCommentId());
            articleInfo.setThumbsUp(record.getThumbsUp());
            articleInfo.setType(record.getType());
            articleInfo.setViews(record.getViews());
            articleInfo.setArtImages(artImages);
            articleInfos.add(articleInfo);
        }
        return articleInfos;
    }

    @Override
    public ArticleInfo getArticleInfos(Article article) {
        ArticleInfo articleInfo = new ArticleInfo();
        QueryWrapper<ArtImages> wrapper = new QueryWrapper<>();
        wrapper.eq("art_id",article.getId());
        List<ArtImages> artImages = artImagesService.getBaseMapper().selectList(wrapper);
        articleInfo.setId(article.getId());
        articleInfo.setAuthorId(article.getAuthorId());
        articleInfo.setCreateTime(article.getCreateTime());
        articleInfo.setUpdateTime(article.getUpdateTime());
        articleInfo.setContent(article.getContent());
        articleInfo.setCommentId(article.getCommentId());
        articleInfo.setThumbsUp(article.getThumbsUp());
        articleInfo.setType(article.getType());
        articleInfo.setViews(article.getViews());
        articleInfo.setArtImages(artImages);

        return articleInfo;
    }
}
