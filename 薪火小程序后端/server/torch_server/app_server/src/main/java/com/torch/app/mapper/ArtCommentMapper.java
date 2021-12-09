package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.ArtComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArtCommentMapper extends BaseMapper<ArtComment> {
}
