package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.TorchPower;

public interface TorchPowerService extends IService<TorchPower> {

    Integer add(Integer uid, Integer publish_id, Integer suggest_id, Integer birth_id);

}
