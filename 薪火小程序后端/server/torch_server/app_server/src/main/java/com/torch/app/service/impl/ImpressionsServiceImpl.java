package com.torch.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ImpImages;
import com.torch.app.entity.Impressions;
import com.torch.app.entity.vo.ImpressionsCon.ImpressionsInfo;
import com.torch.app.mapper.ImpressionsMapper;
import com.torch.app.service.ImpImagesService;
import com.torch.app.service.ImpressionsService;
import com.torch.app.util.commonutils.CacheCode;
import com.torch.app.util.tools.FileUtil;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ImpressionsServiceImpl extends ServiceImpl<ImpressionsMapper, Impressions> implements ImpressionsService {

    private ImpImagesService impImagesService;

    private RedissonClient redissonClient;

    @Autowired
    public ImpressionsServiceImpl(ImpImagesService impImagesService, RedissonClient redissonClient) {
        this.impImagesService = impImagesService;
        this.redissonClient = redissonClient;
    }

    @Override
    public void insertImages(String[] imagesUrls, Integer impId) {
        for (String url : imagesUrls) {
            ImpImages impImages = new ImpImages();
            impImages.setUrl(url);
            impImages.setImpId(impId);
            impImagesService.getBaseMapper().insert(impImages);
            RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
            bloomFilter.add(CacheCode.CACHE_IMP_IMAGES+impImages.getId());
            redissonClient.getBucket(CacheCode.CACHE_IMP_IMAGES+impImages.getId()).trySet(impImages,CacheCode.IMP_IMAGES_TIME, TimeUnit.MINUTES);
        }
    }

    @Override
    public void updateImages(String[] imagesUrls, Integer impId) {
        QueryWrapper<ImpImages> wrapper = new QueryWrapper<>();
        wrapper.eq("imp_id",impId);
        impImagesService.getBaseMapper().delete(wrapper);//这里先删除以前的图片链接
        for (String url : imagesUrls) {
            ImpImages impImages = new ImpImages();
            impImages.setUrl(url);
            impImages.setImpId(impId);
            impImagesService.getBaseMapper().insert(impImages);//再重新插入新的图片及对应id
            RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
            bloomFilter.add(CacheCode.CACHE_IMP_IMAGES+impImages.getId());
            redissonClient.getBucket(CacheCode.CACHE_IMP_IMAGES+impImages.getId()).trySet(impImages,CacheCode.IMP_IMAGES_TIME, TimeUnit.MINUTES);
        }
    }

    @Override
    public ImpressionsInfo getImpressionsInfo(Impressions impression) {
        ImpressionsInfo impressionsInfo = new ImpressionsInfo();
        QueryWrapper<ImpImages> wrapper = new QueryWrapper<>();
        wrapper.eq("imp_id",impression.getId());
        //这里没有用布隆过滤器，因为缓存中的image没有使用impid。
        List<ImpImages> impImages = impImagesService.getBaseMapper().selectList(wrapper);
        List<String> imgUrls = new ArrayList<>();
        for (ImpImages impImage : impImages) {
            imgUrls.add(impImage.getUrl());
        }
        impressionsInfo.setImpImages(imgUrls);
        impressionsInfo.setActId(impression.getActId());
        impressionsInfo.setContent(impression.getContent());
        impressionsInfo.setActStars(impression.getActStars());
        impressionsInfo.setCreateTime(impression.getCreateTime());
        impressionsInfo.setUpdateTime(impression.getUpdateTime());
        impressionsInfo.setId(impression.getId());
        impressionsInfo.setUserId(impression.getUserId());
        impressionsInfo.setActChiId(impression.getActChiId());
        impressionsInfo.setActTimesId(impression.getActTimesId());
        return impressionsInfo;
    }
}
