package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 志愿编辑权限表
 */
@TableName("torch_publish_level")
@Data
public class TorchPublishLevel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer edit;
    private Integer see;
    private Integer uid;
}
