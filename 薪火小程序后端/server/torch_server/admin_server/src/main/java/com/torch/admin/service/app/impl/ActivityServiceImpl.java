package com.torch.admin.service.app.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.app.Activity;
import com.torch.admin.entity.app.vo.PublishActivity;
import com.torch.admin.mapper.app.ActivityMapper;
import com.torch.admin.service.app.ActivityService;
import org.springframework.stereotype.Service;

@Service
@DS("app")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Override
    public Integer addActivity(PublishActivity publishActivity) {
        Activity activity = new Activity();
        activity.setCreaterId(publishActivity.getCreaterId());
        activity.setOrganizer(publishActivity.getOrganizer());
        activity.setIdentifier(publishActivity.getIdentifier());
        activity.setRemarks(publishActivity.getRemarks());
        activity.setAttention(publishActivity.getAttention());
        activity.setContent(publishActivity.getContent());
        activity.setHeadcount(publishActivity.getHeadcount());
        activity.setCreaterId(publishActivity.getCreaterId());
        activity.setTotalNumber(publishActivity.getTotalNumber());
        activity.setActImage(publishActivity.getActImage());
        activity.setCreateTime(new DateTime());
        baseMapper.insert(activity);
        return activity.getId();
    }
}
