package com.torch.admin.controller.robot;

import com.torch.admin.service.GroupReduceService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "GroupReduceController", value = "离群事件")
@RestController
@RequestMapping("/admin/groupReduce")
public class GroupReduceController {

    @Autowired
    private GroupReduceService service;

    @ApiOperation("获取所有群离群信息")
    @GetMapping("/getAllGroupReduce")
    public R getAllGroupReduce() {
        return R.ok().data("content", service.getAllGroupReduce());
    }

}
