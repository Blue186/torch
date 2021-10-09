package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchBirthLevel;
import com.torch.admin.mapper.torch.TorchBirthLevelMapper;
import com.torch.admin.service.torch.TorchBirthLevelService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class TorchBirthLevelServiceImpl extends ServiceImpl<TorchBirthLevelMapper, TorchBirthLevel> implements TorchBirthLevelService {
}
