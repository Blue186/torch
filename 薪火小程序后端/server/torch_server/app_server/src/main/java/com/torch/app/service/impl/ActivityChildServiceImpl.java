package com.torch.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ActivityChild;
import com.torch.app.entity.ActivityTimes;
import com.torch.app.entity.vo.ActivityChildCon.GetChild;
import com.torch.app.mapper.ActivityChildMapper;
import com.torch.app.service.ActivityChildService;
import com.torch.app.service.ActivityTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityChildServiceImpl extends ServiceImpl<ActivityChildMapper, ActivityChild> implements ActivityChildService {
    @Resource
    private ActivityChildMapper activityChildMapper;

    @Resource
    private ActivityTimesService activityTimesService;
    /**
     * 获取所有的子志愿活动
     * @param activityId 父id
     * @return 方法
     */
    @Override
    public List<ActivityChild> selectChild(Integer activityId) {
        return activityChildMapper.selectChild(activityId);
    }

    @Override
    public GetChild setGetChild(ActivityChild activityChild) {
        QueryWrapper<ActivityTimes> wrapper = new QueryWrapper<>();
        wrapper.eq("act_chi_id",activityChild.getId());
        wrapper.eq("act_id",activityChild.getActivityId());
//        wrapper.orderByDesc("start_time");字符串不管用
        List<ActivityTimes> activityTimes = activityTimesService.getBaseMapper().selectList(wrapper);

        GetChild getChild = new GetChild();
        getChild.setId(activityChild.getId());
        getChild.setActivityId(activityChild.getActivityId());
        getChild.setCreateTime(activityChild.getCreateTime());
        getChild.setUpdateTime(activityChild.getUpdateTime());
        getChild.setServicePeriod(activityChild.getServicePeriod());
        getChild.setActivityTimes(activityTimes);
        return getChild;
    }
}
