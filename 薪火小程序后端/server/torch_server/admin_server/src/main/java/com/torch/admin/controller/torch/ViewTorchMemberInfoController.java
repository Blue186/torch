package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.ViewTorchMemberInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "ViewTorchMemberInfoController",value = "成员信息视图相关接口（主要信息查看在此）")
@RestController
@RequestMapping("/admin/viewTorchMemberInfo")
public class ViewTorchMemberInfoController {
    @Resource
    private ViewTorchMemberInfoService viewTorchMemberInfoService;

}
