package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ActivityTimes;
import com.torch.app.mapper.ActivityTimesMapper;
import com.torch.app.service.ActivityTimesService;
import org.springframework.stereotype.Service;

@Service
public class ActivityTimesServiceImpl extends ServiceImpl<ActivityTimesMapper, ActivityTimes> implements ActivityTimesService {
}
