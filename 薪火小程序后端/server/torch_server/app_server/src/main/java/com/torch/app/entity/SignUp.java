package com.torch.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录信息表
 */
@TableName("sign_up")
@Data
public class SignUp implements Serializable {
    @ApiModelProperty(name = "id",value = "报名信息id")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(name = "userId",value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "actChild",value = "子志愿活动id")
    private Integer actChiId;
    @ApiModelProperty(name = "createTime",value = "创建时间")
    private Long createTime;
    @ApiModelProperty(name = "isOver",value = "活动是否结束")
    private Integer isOver;
    @ApiModelProperty(name = "actId",value = "父活动id")
    private Integer actId;
    @ApiModelProperty(name = "actTimesId",value = "具体活动时间段id")
    private Integer actTimesId;
    @ApiModelProperty(name = "impWrote",value = "用户该活动是否填写了心得，0表示未填写，1表示填了")
    private Integer impWrote;
}
