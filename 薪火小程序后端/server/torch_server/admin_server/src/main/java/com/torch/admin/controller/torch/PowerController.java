package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.torch.admin.entity.torch.TorchBirthLevel;
import com.torch.admin.entity.torch.TorchPublishLevel;
import com.torch.admin.entity.torch.TorchSuggestLevel;
import com.torch.admin.entity.torch.ViewTorchMemberInfo;
import com.torch.admin.service.torch.TorchBirthLevelService;
import com.torch.admin.service.torch.TorchPublishLevelService;
import com.torch.admin.service.torch.TorchSuggestLevelService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用于统一分配权限，权限同意提交
 */
@Api(tags = "PowerController", value = "权限统一修改")
@RestController
@RequestMapping("/admin/power")
public class PowerController {
    @Resource
    private TorchBirthLevelService birthLevelService;
    @Resource
    private TorchPublishLevelService publishLevelService;
    @Resource
    private TorchSuggestLevelService suggestLevelService;

    public PowerController() {
    }
    @ApiOperation(value = "权限更改接口")
    @PutMapping("/updatePower")
    public R updatePower(@ApiParam(name = "member",value = "成员的权限信息",required = true)ViewTorchMemberInfo memberInfo){
      //传给我的是一个map集合，那么我要怎么每个权限修改到具体的表呢，根据service每个进行比对？试一试吧
        TorchBirthLevel birthLevel = new TorchBirthLevel();
        TorchPublishLevel publishLevel = new TorchPublishLevel();
        TorchSuggestLevel suggestLevel = new TorchSuggestLevel();

        birthLevel.setSee(memberInfo.getBSee());
        UpdateWrapper<TorchBirthLevel> updateWrapperB = new UpdateWrapper<>();
        updateWrapperB.eq("uid",memberInfo.getId());//获取该用户和的id
        int updateB = birthLevelService.getBaseMapper().update(birthLevel, updateWrapperB);

        publishLevel.setWrite(memberInfo.getWrite());
        publishLevel.setSee(memberInfo.getPSee());
        UpdateWrapper<TorchPublishLevel> updateWrapperP = new UpdateWrapper<>();
        updateWrapperP.eq("uid",memberInfo.getId());
        int updateP = publishLevelService.getBaseMapper().update(publishLevel, updateWrapperP);

        suggestLevel.setLevel(memberInfo.getLevel());
        UpdateWrapper<TorchSuggestLevel> updateWrapperS = new UpdateWrapper<>();
        updateWrapperS.eq("uid",memberInfo.getId());
        int updateS = suggestLevelService.getBaseMapper().update(suggestLevel, updateWrapperS);

        if (updateB==1&&updateP==1&&updateS==1){
            return R.ok();
        }else {
            return R.error().message("权限赋予失败！");
        }
    }


}
