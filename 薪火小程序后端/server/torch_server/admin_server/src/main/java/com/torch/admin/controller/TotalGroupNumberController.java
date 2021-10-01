package com.torch.admin.controller;

import com.torch.admin.service.TotalGroupNumberService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "TotalGroupNumberController", value = "所有群人数统计")
@RestController
@RequestMapping("/admin/totalGroupNumber")
public class TotalGroupNumberController {

    @Autowired
    private TotalGroupNumberService service;

    @ApiOperation(value = "统计总人数")
    @GetMapping("/getLast")
    public R getLast() {
        return R.ok().data("content", service.getLast());
    }

}
