package com.torch.admin.service.robot.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<GroupNumber> getGroupNumberList(String groupNumber, Integer limit) {
        QueryWrapper<GroupNumber> wrapper = new QueryWrapper<>();
        wrapper.eq("group_code", groupNumber).orderByDesc("id").last("limit " + limit);
        List<GroupNumber> numberList = baseMapper.selectList(wrapper);
        return numberList;
    }
}
