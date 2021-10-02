package com.torch.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.GroupReduce;
import com.torch.admin.mapper.GroupReduceMapper;
import com.torch.admin.service.GroupReduceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupReduceServiceImpl extends ServiceImpl<GroupReduceMapper, GroupReduce> implements GroupReduceService {
    @Override
    public List<GroupReduce> getAllGroupReduce() {
        return baseMapper.selectList(null);
    }
}
