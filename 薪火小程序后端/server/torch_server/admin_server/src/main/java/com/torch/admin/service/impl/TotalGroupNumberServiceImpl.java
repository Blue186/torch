package com.torch.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.TotalGroupNumber;
import com.torch.admin.mapper.TotalGroupNumberMapper;
import com.torch.admin.service.TotalGroupNumberService;
import org.springframework.stereotype.Service;

@Service
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
