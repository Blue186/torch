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



}
