package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.ActivityTimes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityTimesMapper extends BaseMapper<ActivityTimes> {
}
