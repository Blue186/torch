package com.torch.admin.service.torch.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.torch.EventLog;
import com.torch.admin.mapper.torch.EventLogMapper;
import com.torch.admin.service.torch.EventLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("admin")
public class EventLogServiceImpl extends ServiceImpl<EventLogMapper, EventLog> implements EventLogService {
    @Override
    public List<EventLog> getEventLog(Integer page, Integer limit) {
        IPage<EventLog> iPage = new Page<>(page, limit);
        QueryWrapper<EventLog> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        iPage = baseMapper.selectPage(iPage, wrapper);
        return iPage.getRecords();
    }
}
