package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchSuggestLevel;
import com.torch.admin.mapper.torch.TorchSuggestLevelMapper;
import com.torch.admin.service.torch.TorchSuggestLevelService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class TorchSuggestLevelServiceImpl extends ServiceImpl<TorchSuggestLevelMapper, TorchSuggestLevel> implements TorchSuggestLevelService {
}
