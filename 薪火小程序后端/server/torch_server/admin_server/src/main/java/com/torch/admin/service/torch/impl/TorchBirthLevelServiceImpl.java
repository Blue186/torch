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
    @Override
    public Integer add(Integer see, Integer uid) {
        TorchBirthLevel torchBirthLevel = new TorchBirthLevel();
        torchBirthLevel.setSee(see);
        torchBirthLevel.setUid(uid);
        baseMapper.insert(torchBirthLevel);
        return torchBirthLevel.getId();
    }
}
