package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.ViewTorchMemberInfo;
import com.torch.admin.mapper.torch.ViewTorchMemberInfoMapper;
import com.torch.admin.service.torch.ViewTorchMemberInfoService;
import org.springframework.stereotype.Service;

@Service
@DS("admin")
public class ViewTorchMemberInfoServiceImpl extends ServiceImpl<ViewTorchMemberInfoMapper, ViewTorchMemberInfo> implements ViewTorchMemberInfoService {

}
