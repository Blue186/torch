package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限分配父表
 */
@TableName("torch_power")
@Data
public class TorchPower {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer publishId;
    private Integer suggestId;
    private Integer birthId;

    private Integer isRoot;
}
