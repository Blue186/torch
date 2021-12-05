package com.torch.admin.entity.app.vo;

import com.torch.admin.entity.torch.TorchMember;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PassedActivity {

    private Integer id;
    private String identifier;
    private TorchMember torchMember;
    private long createTime;
    private long passTime;
    private String organizer;
    private Integer headcount;
    private String remarks;
    private String content;
    private Integer totalNumber;
    private String attention;
    private String actImage;
    private String qqNumber;

    public List<PassedActivityChild> children = new ArrayList<>();

    public PassedActivity() {
    }

    public PassedActivity(Integer id, String identifier, TorchMember torchMember, long createTime, long passTime, String organizer, Integer headcount, String remarks, String content, Integer totalNumber, String attention, String actImage, String qqNumber) {
        this.id = id;
        this.identifier = identifier;
        this.torchMember = torchMember;
        this.createTime = createTime;
        this.passTime = passTime;
        this.organizer = organizer;
        this.headcount = headcount;
        this.remarks = remarks;
        this.content = content;
        this.totalNumber = totalNumber;
        this.attention = attention;
        this.actImage = actImage;
        this.qqNumber = qqNumber;
    }
}
