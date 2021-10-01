package com.torch.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.torch.app.entity.ActivityChild;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityChildMapper extends BaseMapper<ActivityChild> {
    List<ActivityChild> selectChild(Integer activityId);
}
