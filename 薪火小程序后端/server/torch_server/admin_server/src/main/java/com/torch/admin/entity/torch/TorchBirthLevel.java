package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 生日查看权限表
 */

@TableName("torch_birth_level")
@Data
public class TorchBirthLevel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer see;
    private Integer uid;
}
