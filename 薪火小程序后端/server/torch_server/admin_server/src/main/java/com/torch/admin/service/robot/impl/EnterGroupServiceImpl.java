package com.torch.admin.service.robot.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.EnterGroup;
import com.torch.admin.mapper.robot.EnterGroupMapper;
import com.torch.admin.service.robot.EnterGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterGroupServiceImpl extends ServiceImpl<EnterGroupMapper, EnterGroup> implements EnterGroupService {
    @Override
    public List<EnterGroup> getAllEnterGroup() {
        List<EnterGroup> groupList = baseMapper.selectList(null);
        return groupList;
    }
}
