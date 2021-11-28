package com.torch.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.torch.app.entity.ArtComment;
import com.torch.app.entity.vo.CommentCon.PublishComment;
import com.torch.app.mapper.ArtCommentMapper;
import com.torch.app.service.ArtCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ArtCommentServiceImpl extends ServiceImpl<ArtCommentMapper, ArtComment> implements ArtCommentService {
    @Resource
    private ArtCommentMapper artCommentMapper;

    @Override
    public ArtComment setArtCommit(Integer id, PublishComment publishCom) {
        ArtComment artComment = baseMapper.selectById(id);
        artComment.setContent(publishCom.getContent());
        artComment.setArtId(publishCom.getArtId());
        artComment.setAuthorId(id);
        artComment.setCreateTime(new Date().getTime());
        artComment.setUpdateTime(new Date().getTime());
        artComment.setThumbsUp(0);
        return artComment;
    }
}
