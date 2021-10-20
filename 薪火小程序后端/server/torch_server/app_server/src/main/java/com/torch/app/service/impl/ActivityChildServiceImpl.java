package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ActivityChild;
import com.torch.app.mapper.ActivityChildMapper;
import com.torch.app.service.ActivityChildService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityChildServiceImpl extends ServiceImpl<ActivityChildMapper, ActivityChild> implements ActivityChildService {
    @Resource
    private ActivityChildMapper activityChildMapper;

    /**
     * 获取所有的子志愿活动
     * @param activityId 父id
     * @return 方法
     */
    @Override
    public List<ActivityChild> selectChild(Integer activityId) {
        return activityChildMapper.selectChild(activityId);
    }
}
