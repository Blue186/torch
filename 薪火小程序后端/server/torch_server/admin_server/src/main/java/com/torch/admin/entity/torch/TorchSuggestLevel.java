package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 建议查看权限表
 */
@TableName("torch_suggest_level")
@Data
public class TorchSuggestLevel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer level;
    private Integer uid;
}
