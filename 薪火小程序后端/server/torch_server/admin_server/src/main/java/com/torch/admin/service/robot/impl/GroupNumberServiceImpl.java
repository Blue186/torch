package com.torch.admin.service.robot.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.GroupNumber;
import com.torch.admin.mapper.robot.GroupNumberMapper;
import com.torch.admin.service.robot.GroupNumberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("robot")
public class GroupNumberServiceImpl extends ServiceImpl<GroupNumberMapper, GroupNumber> implements GroupNumberService {
    @Override
    public List<GroupNumber> getGroupNumberList() {
        List<GroupNumber> numberList = baseMapper.selectList(null);
        return numberList;
    }
}
