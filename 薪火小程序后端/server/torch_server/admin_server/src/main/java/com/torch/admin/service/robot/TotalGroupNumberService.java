package com.torch.admin.service.robot;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.robot.TotalGroupNumber;

public interface TotalGroupNumberService extends IService<TotalGroupNumber> {

    public TotalGroupNumber getLast();

}
