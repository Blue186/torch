package com.torch.admin.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.app.ActivityTimes;
import com.torch.admin.entity.app.vo.PublishActivityChild;


public interface ActivityTimesService extends IService<ActivityTimes> {
    Integer addOne(PublishActivityChild child, Integer activityId, Integer childId);

    ActivityTimes selectByChildId(Integer childId);
}
