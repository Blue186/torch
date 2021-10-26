package com.torch.admin.mapper.torch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.admin.entity.torch.EventLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventLogMapper extends BaseMapper<EventLog> {
}
