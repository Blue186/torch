package com.torch.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.EnterGroup;

import java.util.List;

public interface EnterGroupService extends IService<EnterGroup> {

    List<EnterGroup> getAllEnterGroup();

}
