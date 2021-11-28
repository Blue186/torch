package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.UserCon.UserInfo;
import com.torch.app.mapper.UserMapper;
import com.torch.app.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User setUserInfo(Integer id,UserInfo userInfo) {
        User user = baseMapper.selectById(id);
        if (userInfo.getPhone()==null||userInfo.getQq()==null||userInfo.getVolAccount()==null||userInfo.getEmail()==null||userInfo.getName()==null){
            user.setIsActive(0);
        }else {
            user.setIsActive(1);
        }
        user.setName(userInfo.getName());
        user.setPhone(userInfo.getPhone());
        user.setQq(userInfo.getQq());
        user.setSchool(userInfo.getSchool());
        user.setGrade(userInfo.getGrade());
        user.setVolAccount(userInfo.getVolAccount());
        user.setNickName(userInfo.getNickName());
        user.setAvatarImage(userInfo.getAvatarImage());
        user.setSelfIntro(userInfo.getSelfIntro());
        user.setSex(userInfo.getSex());
        return user;
    }
}
