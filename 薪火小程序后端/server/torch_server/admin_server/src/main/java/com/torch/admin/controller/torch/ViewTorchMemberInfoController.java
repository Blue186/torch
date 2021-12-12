package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.admin.entity.torch.ViewTorchMemberInfo;
import com.torch.admin.service.torch.ViewTorchMemberInfoService;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"成员信息视图相关接口（主要信息查看在此）"},value = "成员信息视图相关接口（主要信息查看在此）")
@RestController
@RequestMapping("/admin/viewTorchMemberInfo")
public class ViewTorchMemberInfoController {
    @Resource
    private ViewTorchMemberInfoService viewTorchMemberInfoService;

    @ApiOperation("成员所有的信息表，包括权限等")
    @GetMapping("/{id}")
    public R<?> selectOne(@ApiParam(name = "",value = "",required = true) @PathVariable Integer id){
        return R.ok().data(viewTorchMemberInfoService.getBaseMapper().selectById(id));
    }

    @ApiOperation("所有成员的所有信息")
    @GetMapping("/{current}/{limit}")
    public R<?> selectAll(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                          @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit){
        Page<ViewTorchMemberInfo> page = new Page<>(current,limit);
        Page<ViewTorchMemberInfo> infoPage = viewTorchMemberInfoService.getBaseMapper().selectPage(page, null);
        return R.ok().data(infoPage);
    }
}
