package com.torch.app.entity.vo.ImpressionsCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class PublishImpressions implements Serializable {
    @ApiModelProperty(name = "content",value = "心得内容")
    private String content;
    @ApiModelProperty(name = "static/images",value = "心得部分对应上传的图片")
    private String[] imagesUrls;
    @ApiModelProperty(name = "actStars",value = "对志愿活动的评级，一星两星。。")
    private Integer actStars;
    @ApiModelProperty(name = "actId",value = "活动id")
    private Integer actId;
}
