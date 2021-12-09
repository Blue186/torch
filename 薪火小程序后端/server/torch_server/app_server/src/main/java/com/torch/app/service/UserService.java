package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.UserCon.UserInfo;

public interface UserService extends IService<User> {
    User setUserInfo(Integer id,UserInfo userInfo);
}
