package com.torch.app.entity.vo.ImpressionsCon;

import com.torch.app.entity.ImpImages;
import com.torch.app.entity.Impressions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImpressionsInfo extends Impressions implements Serializable {
    @ApiModelProperty(name = "impImages",value = "心得图片")
    private List<String> impImages;
}
