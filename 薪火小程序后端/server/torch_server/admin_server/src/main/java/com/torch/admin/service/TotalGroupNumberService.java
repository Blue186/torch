package com.torch.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.TotalGroupNumber;

public interface TotalGroupNumberService extends IService<TotalGroupNumber> {

    public TotalGroupNumber getLast();

}
