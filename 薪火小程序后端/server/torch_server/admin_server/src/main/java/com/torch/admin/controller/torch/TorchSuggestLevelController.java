package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.TorchPublishLevelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "TorchSuggestLevelController",value = "建议查看相关接口")
@RestController
@RequestMapping("/admin/torchSuggestLevel")
public class TorchSuggestLevelController {
    @Resource
    private TorchPublishLevelService publishLevelService;

}
