package com.torch.admin.service.torch.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchPower;
import com.torch.admin.mapper.torch.TorchPowerMapper;
import com.torch.admin.service.torch.TorchPowerService;
import org.springframework.stereotype.Service;

@Service
public class TorchPowerServiceImpl extends ServiceImpl<TorchPowerMapper, TorchPower> implements TorchPowerService {
}
