package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchPower;
import com.torch.admin.mapper.torch.TorchPowerMapper;
import com.torch.admin.service.torch.TorchPowerService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class TorchPowerServiceImpl extends ServiceImpl<TorchPowerMapper, TorchPower> implements TorchPowerService {
    @Override
    public Integer add(Integer uid, Integer publish_id, Integer suggest_id, Integer birth_id) {
        TorchPower torchPower = new TorchPower();
        torchPower.setBirthId(birth_id);
        torchPower.setPublishId(publish_id);
        torchPower.setUid(uid);
        torchPower.setSuggestId(suggest_id);
        baseMapper.insert(torchPower);
        return torchPower.getId();
    }
}
