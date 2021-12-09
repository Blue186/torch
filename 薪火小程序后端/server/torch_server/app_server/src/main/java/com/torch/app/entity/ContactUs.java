package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("contact_us")
public class ContactUs {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String content;
    private Date createTime;
    private Date updateTime;
    private String resContent;
    private Date resTime;
}
