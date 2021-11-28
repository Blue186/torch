package com.torch.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ArtImages;
import com.torch.app.entity.Article;
import com.torch.app.entity.ImpImages;
import com.torch.app.entity.Impressions;
import com.torch.app.entity.vo.ArticleCon.ArticleInfo;
import com.torch.app.entity.vo.ImpressionsCon.ImpressionsInfo;
import com.torch.app.mapper.ImpressionsMapper;
import com.torch.app.service.ImpImagesService;
import com.torch.app.service.ImpressionsService;
import com.torch.app.util.tools.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImpressionsServiceImpl extends ServiceImpl<ImpressionsMapper, Impressions> implements ImpressionsService {
    @Resource
    private ImpressionsMapper impressionsMapper;
    @Resource
    private FileUtil fileUtil;
    @Resource
    private ImpImagesService impImagesService;
    @Override
    public void insertImages(MultipartFile[] images, Integer impId) {
        String[] urls = fileUtil.uploadImage(images);
        for (String url : urls) {
            ImpImages impImages = new ImpImages();
            impImages.setUrl(url);
            impImages.setImpId(impId);
            impImagesService.getBaseMapper().insert(impImages);
        }
    }

    @Override
    public void updateImages(MultipartFile[] images, Integer impId) {
        int res = impImagesService.getBaseMapper().deleteById(impId);
        if (res==1){
            String[] urls = fileUtil.uploadImage(images);
            for (String url : urls) {
                ImpImages impImages = new ImpImages();
                impImages.setUrl(url);
                impImages.setImpId(impId);
                impImagesService.getBaseMapper().insert(impImages);
            }
        }
    }

    @Override
    public List<ImpressionsInfo> getImpressionsInfo(List<Impressions> records) {
        List<ImpressionsInfo> impressionsInfos = new ArrayList<>();
        for (Impressions record : records) {
            QueryWrapper<ImpImages> wrapper = new QueryWrapper<>();
            wrapper.eq("imp_id",record.getId());
            List<ImpImages> impImages = impImagesService.getBaseMapper().selectList(wrapper);

            ImpressionsInfo impressionsInfo = new ImpressionsInfo();
            impressionsInfo.setImpImages(impImages);
            impressionsInfo.setActId(record.getActId());
            impressionsInfo.setContent(record.getContent());
            impressionsInfo.setActStars(record.getActStars());
            impressionsInfo.setCreateTime(record.getCreateTime());
            impressionsInfo.setUpdateTime(record.getUpdateTime());
            impressionsInfo.setId(record.getId());
            impressionsInfo.setUserId(record.getUserId());
            impressionsInfos.add(impressionsInfo);
        }
        return impressionsInfos;
    }

    @Override
    public ImpressionsInfo getImpressionsInfo(Impressions impression) {
        ImpressionsInfo impressionsInfo = new ImpressionsInfo();
        QueryWrapper<ImpImages> wrapper = new QueryWrapper<>();
        wrapper.eq("imp_id",impression.getId());
        List<ImpImages> impImages = impImagesService.getBaseMapper().selectList(wrapper);

        impressionsInfo.setImpImages(impImages);
        impressionsInfo.setActId(impression.getActId());
        impressionsInfo.setContent(impression.getContent());
        impressionsInfo.setActStars(impression.getActStars());
        impressionsInfo.setCreateTime(impression.getCreateTime());
        impressionsInfo.setUpdateTime(impression.getUpdateTime());
        impressionsInfo.setId(impression.getId());
        impressionsInfo.setUserId(impression.getUserId());

        return impressionsInfo;
    }
}
