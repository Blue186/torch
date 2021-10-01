package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.SignUp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignUpMapper extends BaseMapper<SignUp> {
}
