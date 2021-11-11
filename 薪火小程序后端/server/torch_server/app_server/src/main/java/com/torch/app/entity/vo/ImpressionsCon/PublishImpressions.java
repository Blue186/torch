package com.torch.app.entity.vo.ImpressionsCon;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class PublishImpressions implements Serializable {
    private String content;
    private MultipartFile[] images;
    private Integer actStars;
    private Integer actId;
}
