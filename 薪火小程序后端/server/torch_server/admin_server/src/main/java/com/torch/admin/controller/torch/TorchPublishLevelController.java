package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.TorchPublishLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"志愿任务发布权限相关接口"},value = "志愿任务发布权限相关接口")
@RestController
@RequestMapping("/admin/torchPublishLevel")
public class TorchPublishLevelController {
    @Resource
    private TorchPublishLevelService publishLevelService;
}
