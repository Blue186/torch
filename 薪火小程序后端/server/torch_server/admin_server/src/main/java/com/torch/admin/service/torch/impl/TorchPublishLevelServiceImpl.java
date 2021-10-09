package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchPublishLevel;
import com.torch.admin.mapper.torch.TorchPublishLevelMapper;
import com.torch.admin.service.torch.TorchPublishLevelService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class TorchPublishLevelServiceImpl extends ServiceImpl<TorchPublishLevelMapper, TorchPublishLevel> implements TorchPublishLevelService {
}
