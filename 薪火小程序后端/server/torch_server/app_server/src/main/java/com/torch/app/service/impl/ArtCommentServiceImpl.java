package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ArtComment;
import com.torch.app.mapper.ArtCommentMapper;
import com.torch.app.service.ArtCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArtCommentServiceImpl extends ServiceImpl<ArtCommentMapper, ArtComment> implements ArtCommentService {
    @Resource
    private ArtCommentMapper artCommentMapper;

}
