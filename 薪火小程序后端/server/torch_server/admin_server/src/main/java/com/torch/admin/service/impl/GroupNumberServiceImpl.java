package com.torch.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.GroupNumber;
import com.torch.admin.mapper.GroupNumberMapper;
import com.torch.admin.service.GroupNumberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupNumberServiceImpl extends ServiceImpl<GroupNumberMapper, GroupNumber> implements GroupNumberService {
    @Override
    public List<GroupNumber> getGroupNumberList() {
        List<GroupNumber> numberList = baseMapper.selectList(null);
        return numberList;
    }
}
