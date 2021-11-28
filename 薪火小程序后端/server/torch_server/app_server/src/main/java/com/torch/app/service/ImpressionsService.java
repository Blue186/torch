package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.Impressions;
import com.torch.app.entity.vo.ImpressionsCon.ImpressionsInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImpressionsService extends IService<Impressions> {
    void insertImages(MultipartFile[] images, Integer impId);
    void updateImages(MultipartFile[] images,Integer impId);
    List<ImpressionsInfo> getImpressionsInfo(List<Impressions> records);
    ImpressionsInfo getImpressionsInfo(Impressions impression);
}
