package com.torch.admin.service.app.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.app.ActivityChild;
import com.torch.admin.entity.app.vo.PublishActivityChild;
import com.torch.admin.mapper.app.ActivityChildMapper;
import com.torch.admin.service.app.ActivityChildService;
import org.springframework.stereotype.Service;

@Service
@DS("app")
public class ActivityChildServiceImpl extends ServiceImpl<ActivityChildMapper, ActivityChild> implements ActivityChildService {
    @Override
    public Integer addActivityChild(PublishActivityChild publishActivityChild, Integer activityId) {
        ActivityChild activityChild = new ActivityChild();
        activityChild.setActivityId(activityId);
        activityChild.setCreateTime(new DateTime());
        activityChild.setUpdateTime(new DateTime());
        activityChild.setNumber(publishActivityChild.getNumber());
        activityChild.setVolTime(publishActivityChild.getVolTime());
        activityChild.setStartTime(publishActivityChild.getStartTime());
        activityChild.setEndTime(publishActivityChild.getEndTime());
        activityChild.setAddress(publishActivityChild.getAddress());
        baseMapper.insert(activityChild);
        return activityChild.getId();
    }
}
