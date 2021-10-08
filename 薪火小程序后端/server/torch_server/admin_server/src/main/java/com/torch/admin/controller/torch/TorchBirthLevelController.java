package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.torch.admin.entity.torch.TorchBirthLevel;
import com.torch.admin.service.torch.TorchBirthLevelService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api(tags = "TorchBirthLevelController", value = "成员生日权限查看相关接口")
@RestController
@RequestMapping("/admin/torchBirth")
public class TorchBirthLevelController {
    @Resource
    private TorchBirthLevelService birthLevelService;

//    @ApiOperation("赋予see的权限接口")
//    @PutMapping("/endowSee")
//    public R endowSeeLevel(@ApiParam(name = "id",value = "用户的id",required = true) Integer id){
//        TorchBirthLevel birthLevel = new TorchBirthLevel();
//        birthLevel.setSee(1);
//        UpdateWrapper<TorchBirthLevel> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("id",id);
//        int update = birthLevelService.getBaseMapper().update(birthLevel, updateWrapper);
//        if (update==1){
//            return R.ok();
//        }else {
//            return R.error().message("权限赋予失败");
//        }
//    }

}
