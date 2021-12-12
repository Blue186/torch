package com.torch.admin.mapper.torch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.admin.entity.torch.TorchMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TorchMemberMapper extends BaseMapper<TorchMember> {
}
