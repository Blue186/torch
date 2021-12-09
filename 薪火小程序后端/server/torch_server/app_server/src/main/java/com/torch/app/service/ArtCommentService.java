package com.torch.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.torch.app.entity.ArtComment;
import com.torch.app.entity.vo.CommentCon.PublishComment;

public interface ArtCommentService extends IService<ArtComment> {
    ArtComment setArtCommit(Integer id, PublishComment publishCom);
}
