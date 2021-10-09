package com.torch.admin.service.robot;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.robot.GroupReduce;

import java.util.List;

public interface GroupReduceService extends IService<GroupReduce> {

    List<GroupReduce> getAllGroupReduce();

}
