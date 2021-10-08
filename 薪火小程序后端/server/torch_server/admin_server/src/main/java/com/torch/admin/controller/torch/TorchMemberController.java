package com.torch.admin.controller.torch;

import com.torch.admin.service.torch.TorchMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "TorchMemberController", value = "成员相关接口")
@RestController
@RequestMapping("/admin/torchMember")
public class TorchMemberController {

    @Resource
    private TorchMemberService memberService;

}
