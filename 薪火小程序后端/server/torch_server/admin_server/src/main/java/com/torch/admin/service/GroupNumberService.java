package com.torch.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.GroupNumber;

import java.util.List;

public interface GroupNumberService extends IService<GroupNumber> {

    List<GroupNumber> getGroupNumberList();

}
