package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@TableName("impressions")
public class Impressions {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer actId;
    private String content;
    private Long createTime;
    private Long updateTime;
    private Integer actStars;
}
