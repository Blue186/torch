package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.Impressions;
import com.torch.app.mapper.ImpressionsMapper;
import com.torch.app.service.ImpressionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ImpressionsServiceImpl extends ServiceImpl<ImpressionsMapper, Impressions> implements ImpressionsService {
    @Resource
    private ImpressionsMapper impressionsMapper;
}
