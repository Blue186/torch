package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

}
