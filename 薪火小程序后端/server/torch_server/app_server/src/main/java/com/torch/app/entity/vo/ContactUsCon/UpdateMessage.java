package com.torch.app.entity.vo.ContactUsCon;

import lombok.Data;

import java.io.Serializable;
@Data
public class UpdateMessage implements Serializable {
    private Integer id;
    private String content;
}
