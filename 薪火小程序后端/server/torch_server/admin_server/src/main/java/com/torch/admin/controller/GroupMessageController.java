package com.torch.admin.controller;

import com.torch.admin.service.GroupMessageService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "GroupMessageController", value = "群信息")
@RestController
@RequestMapping("/admin/groupMessage")
public class GroupMessageController {

    @Autowired
    private GroupMessageService service;

    @ApiOperation("获取所有群消息")
    @GetMapping("/admin/getAllGroupMessage")
    public R getAllGroupMessage() {
        return R.ok().data("content", service.getAllGroupMessage());
    }

}
