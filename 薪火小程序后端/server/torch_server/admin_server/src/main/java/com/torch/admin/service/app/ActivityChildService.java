package com.torch.admin.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.app.ActivityChild;
import com.torch.admin.entity.app.vo.PublishActivityChild;

public interface ActivityChildService extends IService<ActivityChild> {

    Integer addActivityChild(PublishActivityChild publishActivityChild, Integer activityId);

}
