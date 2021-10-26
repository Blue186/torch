package com.torch.admin.service.torch;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.torch.EventLog;

import java.util.List;

public interface EventLogService extends IService<EventLog> {

    public List<EventLog> getEventLog(Integer page, Integer limit);

}
