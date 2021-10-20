package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.SignUp;
import com.torch.app.mapper.SignUpMapper;
import com.torch.app.service.SignUpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SignUpServiceImpl extends ServiceImpl<SignUpMapper, SignUp> implements SignUpService {

    @Resource
    private SignUpMapper signUpMapper;

}
