package com.torch.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.GroupMessage;
import com.torch.admin.mapper.GroupMessageMapper;
import com.torch.admin.service.GroupMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMessageServiceImpl extends ServiceImpl<GroupMessageMapper, GroupMessage> implements GroupMessageService {
    @Override
    public List<GroupMessage> getAllGroupMessage() {
        return baseMapper.selectList(null);
    }
}
