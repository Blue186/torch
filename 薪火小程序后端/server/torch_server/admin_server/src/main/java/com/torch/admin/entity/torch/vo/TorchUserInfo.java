package com.torch.admin.entity.torch.vo;

import com.torch.admin.entity.torch.TorchMember;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TorchUserInfo implements Serializable {

    @ApiModelProperty(value = "姓名", name = "nickname")
    private String nickname;

    @ApiModelProperty(value = "QQ 号", name = "accountCode")
    private String accountCode;

    @ApiModelProperty(value = "生日", name = "birthday")
    private String birthday;

    @ApiModelProperty(value = "职位", name = "position")
    private String position;

    @ApiModelProperty(value = "部门", name = "department")
    private String department;

    @ApiModelProperty(value = "成员编号", name = "identifier")
    private String identifier;

    @ApiModelProperty(value = "电话", name = "phone")
    private String phone;

    @ApiModelProperty(value = "入会时间", name = "enterTime")
    private Date enterTime;

    public TorchUserInfo(TorchMember member) {
        this.setBirthday(member.getBirthday());
        this.setAccountCode(member.getAccountCode());
        this.setDepartment(member.getDepartment());
        this.setNickname(member.getNickname());
        this.setPhone(member.getPhone());
        this.setEnterTime(member.getEnterTime());
        this.setIdentifier(member.getIdentifier());
        this.setPhone(member.getPosition());
    }

    public TorchUserInfo() {}

}
