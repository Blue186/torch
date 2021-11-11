package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.Thumbs;
import com.torch.app.mapper.ThumbsMapper;
import com.torch.app.service.ThumbsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ThumbsServiceImpl extends ServiceImpl<ThumbsMapper, Thumbs> implements ThumbsService {
    @Resource
    private ThumbsMapper thumbsMapper;
}
