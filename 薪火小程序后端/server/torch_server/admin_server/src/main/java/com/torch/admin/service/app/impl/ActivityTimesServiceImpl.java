package com.torch.admin.service.app.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.app.ActivityTimes;
import com.torch.admin.entity.app.vo.PublishActivityChild;
import com.torch.admin.mapper.app.ActivityTimesMapper;
import com.torch.admin.service.app.ActivityTimesService;
import org.springframework.stereotype.Service;


@Service
@DS("app")
public class ActivityTimesServiceImpl  extends ServiceImpl<ActivityTimesMapper, ActivityTimes> implements ActivityTimesService {
    @Override
    public Integer addOne(PublishActivityChild child, Integer activityId, Integer childId) {
        ActivityTimes activityTimes = new ActivityTimes();
        activityTimes.setStartTime(child.getStartTime());
        activityTimes.setEndTime(child.getEndTime());
        activityTimes.setAddress(child.getAddress());
        activityTimes.setRecruiting(child.getRecruiting());
        activityTimes.setActId(activityId);
        activityTimes.setActChiId(childId);
        baseMapper.insert(activityTimes);
        return activityTimes.getId();
    }

    @Override
    public ActivityTimes selectByChildId(Integer childId) {
        QueryWrapper<ActivityTimes> wrapper = new QueryWrapper<>();
        wrapper.eq("act_chi_id", childId);
        return baseMapper.selectOne(wrapper);
    }
}
