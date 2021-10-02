package com.torch.admin.controller.robot;

import com.torch.admin.service.GroupNumberService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "GroupNumberController", value = "各个群群人数")
@RestController
@RequestMapping("/admin/groupNumber")
public class GroupNumberController {

    @Autowired
    private GroupNumberService service;

    @ApiOperation("获取群人数所有信息")
    @GetMapping("/getAllGroupNumber")
    public R getAllGroupNumber() {
        return R.ok().data("content", service.getGroupNumberList());
    }

}
