package com.torch.admin.controller.robot;

import com.torch.admin.service.robot.EnterGroupService;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "EnterGroupController", value = "入群记录")
@RestController
@RequestMapping("/admin/enterGroup")
public class EnterGroupController {

    @Autowired
    private EnterGroupService enterGroupService;

    @ApiOperation("获取所有入群消息")
    @GetMapping("/getAllEnterGroup")
    public R<?> getAllEnterGroup() {
        return R.ok().data("content", enterGroupService.getAllEnterGroup());
    }

}
