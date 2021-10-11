package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

//    @ApiOperation(tags = "insertMember",value = "后台管理新增成员接口")
//    @PostMapping()
//    public R<?> insertMember(@ApiParam(name = "member",value = "成员信息",required = true)TorchMember member){
//        int res = memberService.getBaseMapper().insert(member);
//        if (res==1){
//            return R.ok();
//        }else {
//            return R.error().message("新增成员失败！");
//        }
//    }

    @ApiOperation(tags = "deleteMember",value = "后台管理删除成员信息接口")
    @DeleteMapping("/{id}")
    public R<?> deleteMember(@ApiParam(name = "",value = "",required = true)@PathVariable Integer id){
        int res = memberService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("删除成员信息失败！");
        }
    }

    @ApiOperation(tags = "updateMember",value = "后台管理修改成员信息接口")
    @PutMapping()
    public R<?> updateMember(@ApiParam(name = "member",value = "修改后的成员信息",required = true) TorchMember member){
        int res = memberService.getBaseMapper().updateById(member);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("更新成员信息失败！");
        }
    }

    @ApiOperation(tags = "updateSelf",value = "成员个人修改个人信息接口")
    @PutMapping("/selfInfo")
    public R<?> updateSelf(@ApiParam(name = "selfInfo",value = "修改后的成员信息",required = true) Map<String,Object> selfInfo){
        TorchMember member = new TorchMember();
        member.setNickname(selfInfo.get("nickname").toString());
        member.setBirthday(selfInfo.get("birthday").toString());
        member.setPhone(selfInfo.get("phone").toString());
        member.setPassword(selfInfo.get("password").toString());
        member.setAccountCode(selfInfo.get("accountCode").toString());
        UpdateWrapper<TorchMember> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",selfInfo.get("id"));
        int res = memberService.getBaseMapper().update(member, updateWrapper);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("更新成员信息失败！");
        }
    }

    @ApiOperation(tags = "selectMember",value = "成员个人查找成员个人信息接口")
    @GetMapping("/{id}")
    public R<?> selectMember(@ApiParam(name = "",value = "",required = true)@PathVariable Integer id){
        TorchMember member = memberService.getBaseMapper().selectById(id);
        Map<String,Object> info = new HashMap<>();
        info.put("nickname",member.getNickname());
        info.put("accountCode",member.getAccountCode());
        info.put("birthday",member.getBirthday());
        info.put("department",member.getDepartment());
        info.put("identifier", member.getIdentifier());
        info.put("phone",member.getPhone());
        return R.ok().data(info);
    }



}
