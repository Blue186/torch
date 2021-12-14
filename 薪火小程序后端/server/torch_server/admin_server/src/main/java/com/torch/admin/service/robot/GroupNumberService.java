package com.torch.admin.service.robot;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.robot.GroupNumber;

import java.util.List;

public interface GroupNumberService extends IService<GroupNumber> {

    List<GroupNumber> getGroupNumberList(String groupNumber, Integer limit);

}
