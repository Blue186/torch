package com.torch.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.robot.GroupMessage;

import java.util.List;

public interface GroupMessageService extends IService<GroupMessage> {

    List<GroupMessage> getAllGroupMessage();

}
