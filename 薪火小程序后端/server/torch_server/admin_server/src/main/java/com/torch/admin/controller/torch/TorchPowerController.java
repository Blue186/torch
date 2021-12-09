package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.TorchPowerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"用户权限相关"},value = "权限表相关接口")
@RestController
@RequestMapping("/admin/torchPower")
public class TorchPowerController {
    @Resource
    private TorchPowerService powerService;
}
