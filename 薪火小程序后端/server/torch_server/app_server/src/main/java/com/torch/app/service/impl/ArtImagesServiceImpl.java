package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ArtImages;
import com.torch.app.mapper.ArtImagesMapper;
import com.torch.app.service.ArtImagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArtImagesServiceImpl extends ServiceImpl<ArtImagesMapper, ArtImages> implements ArtImagesService {
    @Resource
    private ArtImagesMapper artImagesMapper;

}
