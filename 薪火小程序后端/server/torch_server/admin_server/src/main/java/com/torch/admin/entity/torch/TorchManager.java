package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 管理员
 */

@TableName("torch_manager")
@Data
public class TorchManager {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;

}
