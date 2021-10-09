package com.torch.admin.controller.torch;

import com.torch.admin.entity.torch.TorchMember;
import com.torch.admin.service.torch.TorchBirthLevelService;
import com.torch.admin.service.torch.TorchMemberService;
import com.torch.admin.service.torch.TorchPublishLevelService;
import com.torch.admin.service.torch.TorchSuggestLevelService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "TorchMemberController", value = "成员相关接口")
@RestController
@RequestMapping("/admin/torchMember")
public class TorchMemberController {

    @Resource
    private TorchMemberService memberService;

    @ApiOperation(tags = "insertMember",value = "新增成员接口")
    @PostMapping()
    public R insertMember(@ApiParam(name = "member",value = "成员信息",required = true)TorchMember member){
        int res = memberService.getBaseMapper().insert(member);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("新增成员失败！");
        }
    }

    @ApiOperation(tags = "deleteMember",value = "删除成员信息接口")
    @DeleteMapping("/{id}")
    public R deleteMember(@ApiParam(name = "",value = "",required = true)@PathVariable Integer id){
        int res = memberService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("删除成员信息失败！");
        }
    }

    @ApiOperation(tags = "updateMember",value = "修改成员信息接口")
    @PutMapping()
    public R updateMember(@ApiParam(name = "member",value = "修改后的成员信息",required = true) TorchMember member){
        int res = memberService.getBaseMapper().updateById(member);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("更新成员信息失败！");
        }
    }

    @ApiOperation(tags = "selectMember",value = "查找成员个人信息接口")
    @GetMapping("/{id}")
    public R selectMember(@ApiParam(name = "",value = "",required = true)@PathVariable Integer id){
        TorchMember member = memberService.getBaseMapper().selectById(id);
        return R.ok().data((Map<String, Object>) member);
    }
}
