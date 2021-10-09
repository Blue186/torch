package com.torch.admin.service.robot.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.GroupReduce;
import com.torch.admin.mapper.robot.GroupReduceMapper;
import com.torch.admin.service.robot.GroupReduceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("robot")
public class GroupReduceServiceImpl extends ServiceImpl<GroupReduceMapper, GroupReduce> implements GroupReduceService {
    @Override
    public List<GroupReduce> getAllGroupReduce() {
        return baseMapper.selectList(null);
    }
}
