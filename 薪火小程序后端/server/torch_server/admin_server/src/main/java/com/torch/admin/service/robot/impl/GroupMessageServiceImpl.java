package com.torch.admin.service.robot.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.GroupMessage;
import com.torch.admin.mapper.robot.GroupMessageMapper;
import com.torch.admin.service.robot.GroupMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMessageServiceImpl extends ServiceImpl<GroupMessageMapper, GroupMessage> implements GroupMessageService {
    @Override
    public List<GroupMessage> getAllGroupMessage() {
        return baseMapper.selectList(null);
    }
}
