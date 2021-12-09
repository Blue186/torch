package com.torch.admin.service.app.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.app.ActivityChild;
import com.torch.admin.entity.app.vo.PublishActivityChild;
import com.torch.admin.mapper.app.ActivityChildMapper;
import com.torch.admin.service.app.ActivityChildService;
import com.torch.admin.utils.ActivityUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@DS("app")
public class ActivityChildServiceImpl extends ServiceImpl<ActivityChildMapper, ActivityChild> implements ActivityChildService {
    @Override
    public Integer addActivityChild(PublishActivityChild publishActivityChild, Integer activityId) {
        ActivityChild activityChild = new ActivityChild();
        activityChild.setActivityId(activityId);
        long time = Calendar.getInstance().getTimeInMillis();
        activityChild.setCreateTime(time);
        activityChild.setUpdateTime(time);
        activityChild.setServicePeriod(publishActivityChild.getServicePeriod());
        baseMapper.insert(activityChild);
        return activityChild.getId();
    }

    @Override
    public List<ActivityChild> getByParentId(Integer parentId) {
        QueryWrapper<ActivityChild> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id", parentId);
        List<ActivityChild> activityChildren = baseMapper.selectList(wrapper);
        return activityChildren;
    }
}
