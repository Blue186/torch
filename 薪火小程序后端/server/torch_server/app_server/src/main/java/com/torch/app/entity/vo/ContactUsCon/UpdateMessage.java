package com.torch.app.entity.vo.ContactUsCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class UpdateMessage implements Serializable {
    @ApiModelProperty(name = "id",value = "联系我们（消息）id")
    private Integer id;
    @ApiModelProperty(name = "content",value = "消息内容")
    private String content;
}
