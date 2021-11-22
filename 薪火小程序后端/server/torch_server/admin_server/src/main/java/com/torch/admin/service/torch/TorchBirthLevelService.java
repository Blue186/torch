package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchBirthLevel;

public interface TorchBirthLevelService extends IService<TorchBirthLevel> {

    Integer add(Integer see, Integer uid);

}
