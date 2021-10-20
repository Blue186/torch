package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.ActivityChild;

import java.util.List;

public interface ActivityChildService extends IService<ActivityChild> {
    List<ActivityChild> selectChild(Integer activityId);
}
