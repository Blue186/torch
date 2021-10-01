package com.torch.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("totalgroupnumber")
public class TotalGroupNumber {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer total;
    private Integer sum;
    private String time;

}
