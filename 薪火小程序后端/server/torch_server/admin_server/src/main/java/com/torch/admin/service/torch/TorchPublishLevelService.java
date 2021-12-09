package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchPublishLevel;

public interface TorchPublishLevelService extends IService<TorchPublishLevel> {

    Integer add(Integer edit, Integer see, Integer uid);

}
