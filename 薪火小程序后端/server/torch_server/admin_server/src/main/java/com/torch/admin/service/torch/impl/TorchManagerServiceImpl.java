package com.torch.admin.service.torch.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchManager;
import com.torch.admin.mapper.torch.TorchManagerMapper;
import com.torch.admin.service.torch.TorchManagerService;
import org.springframework.stereotype.Service;

@Service
public class TorchManagerServiceImpl extends ServiceImpl<TorchManagerMapper, TorchManager> implements TorchManagerService {
}
