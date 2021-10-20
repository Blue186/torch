package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
