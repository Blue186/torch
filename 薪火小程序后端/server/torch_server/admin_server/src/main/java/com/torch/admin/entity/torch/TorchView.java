package com.torch.admin.entity.torch;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 存放视图url
 */
@TableName("torch_view")
@Data
public class TorchView {
    private String id;
    private String title;
    private String icon;
    private String route;
}
