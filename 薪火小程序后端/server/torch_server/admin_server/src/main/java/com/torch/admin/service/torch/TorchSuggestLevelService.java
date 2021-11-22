package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchSuggestLevel;

public interface TorchSuggestLevelService extends IService<TorchSuggestLevel> {

    Integer add(Integer level, Integer uid);

}
