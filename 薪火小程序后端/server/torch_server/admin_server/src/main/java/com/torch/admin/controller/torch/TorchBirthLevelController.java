package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.TorchBirthLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api(tags = {"成员生日权限查看相关接口"}, value = "成员生日权限查看相关接口")
@RestController
@RequestMapping("/admin/torchBirth")
public class TorchBirthLevelController {
    @Resource
    private TorchBirthLevelService birthLevelService;



}
