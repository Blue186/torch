package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.TorchView;
import com.torch.admin.mapper.torch.TorchViewMapper;
import com.torch.admin.service.torch.TorchViewService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class TorchViewServiceImpl extends ServiceImpl<TorchViewMapper, TorchView> implements TorchViewService {

}
