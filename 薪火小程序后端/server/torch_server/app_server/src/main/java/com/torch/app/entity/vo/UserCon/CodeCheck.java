package com.torch.app.entity.vo.UserCon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CodeCheck implements Serializable {
     @ApiModelProperty(name = "mail",value = "用户邮箱")
     private String mail;
     @ApiModelProperty(name = "code",value = "邮箱验证码")
     private String code;
}
