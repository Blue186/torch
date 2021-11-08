package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ContactUs;
import com.torch.app.mapper.ContactUsMapper;
import com.torch.app.service.ContactUsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContactUsServiceImpl extends ServiceImpl<ContactUsMapper, ContactUs> implements ContactUsService {
    @Resource
    private ContactUsMapper contactUsMapper;
}
