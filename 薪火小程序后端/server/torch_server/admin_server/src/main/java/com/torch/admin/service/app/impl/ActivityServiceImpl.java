package com.torch.admin.service.app.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.admin.entity.app.Activity;
import com.torch.admin.entity.app.vo.PublishActivity;
import com.torch.admin.mapper.app.ActivityMapper;
import com.torch.admin.service.app.ActivityService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@DS("app")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Override
    public Integer addActivity(PublishActivity publishActivity, Integer uid, String identifier, Integer headcount) {
        Activity activity = new Activity();
        activity.setCreaterId(uid);
        activity.setOrganizer(publishActivity.getOrganizer());
        activity.setIdentifier(identifier);
        activity.setRemarks(publishActivity.getRemarks());
        activity.setAttention(publishActivity.getAttention());
        activity.setContent(publishActivity.getContent());
        activity.setHeadcount(headcount);
        activity.setQqNumber(publishActivity.getQqNumber());
//        activity.setTotalNumber(publishActivity.getTotalNumber());
        activity.setActImage(publishActivity.getActImage());
        Date date = new Date(new java.util.Date().getTime());
        activity.setCreateTime(date);

        activity.setIsPass(1);// 先直接审核通过
        activity.setPassTime(new Date(new java.util.Date().getTime()));

        baseMapper.insert(activity);
        return activity.getId();
    }

    @Override
    public List<Activity> getWaitForPass() {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_pass", 0);
        List<Activity> activities = baseMapper.selectList(wrapper);
        return activities;
    }

    @Override
    public List<Activity> getPassed(Integer page, Integer limit) {
        IPage<Activity> activityPage = new Page<>(page, limit);
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_pass", 1);
        wrapper.orderByDesc("id");
        activityPage = baseMapper.selectPage(activityPage, wrapper);
        List<Activity> activities = activityPage.getRecords();
        return activities;
    }

    @Override
    public List<Activity> getUnPass() {
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_pass", -1);
        List<Activity> activities = baseMapper.selectList(wrapper);
        return activities;
    }

    @Override
    public boolean changePass(Integer activityId, Integer isPass) {
        Activity activity = baseMapper.selectById(activityId);
        activity.setIsPass(isPass);
        int i = baseMapper.updateById(activity);
        return i == 1;
    }
}
