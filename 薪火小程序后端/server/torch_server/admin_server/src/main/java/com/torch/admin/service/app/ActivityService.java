package com.torch.admin.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.admin.entity.app.Activity;
import com.torch.admin.entity.app.vo.PublishActivity;

import java.util.List;

public interface ActivityService extends IService<Activity> {

    Integer addActivity(PublishActivity publishActivity, Integer uid, String identifier, Integer headcount);

    List<Activity> getWaitForPass();

    boolean changePass(Integer activityId, Integer isPass);
}
