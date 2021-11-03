package com.torch.admin.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.app.Activity;
import com.torch.admin.entity.app.vo.PublishActivity;

public interface ActivityService extends IService<Activity> {

    Integer addActivity(PublishActivity publishActivity);

}
