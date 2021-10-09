package com.torch.admin.service.robot.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.robot.TotalGroupNumber;
import com.torch.admin.mapper.robot.TotalGroupNumberMapper;
import com.torch.admin.service.robot.TotalGroupNumberService;
import org.springframework.stereotype.Service;

@Service
@DS("robot")
public class TotalGroupNumberServiceImpl extends ServiceImpl<TotalGroupNumberMapper, TotalGroupNumber> implements TotalGroupNumberService {
    @Override
    public TotalGroupNumber getLast() {
        QueryWrapper<TotalGroupNumber> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 1");
        TotalGroupNumber number = baseMapper.selectOne(wrapper);
        return number;
    }
}
