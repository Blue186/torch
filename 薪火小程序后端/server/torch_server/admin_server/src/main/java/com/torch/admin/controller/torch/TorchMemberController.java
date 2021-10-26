package com.torch.admin.controller.torch;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.torch.admin.entity.torch.EventLog;
import com.torch.admin.entity.torch.TorchMember;
import com.torch.admin.entity.torch.vo.TorchUserInfo;
import com.torch.admin.service.torch.EventLogService;
import com.torch.admin.service.torch.TorchMemberService;
import com.torch.admin.utils.CookieUtils;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"薪火成员信息接口"}, value = "成员相关接口")
@RestController
@RequestMapping("/admin/torchMember")
public class TorchMemberController {

    @Resource
    private TorchMemberService memberService;

    @Resource
    private EventLogService eventLogService;

//    @ApiOperation(tags = "insertMember",value = "后台管理新增成员接口")
//    @PostMapping()
//    public R<?> insertMember(@ApiParam(name = "member",value = "成员信息",required = true)TorchMemberList member){
//        int res = memberService.getBaseMapper().insert(member);
//        if (res==1){
//            return R.ok();
//        }else {
//            return R.error().message("新增成员失败！");
//        }
//    }

    @ApiOperation(tags = "deleteMember", value = "后台管理删除成员信息接口")
    @DeleteMapping("/{id}")
    public R<?> deleteMember(@ApiParam(name = "id", value = "", required = true) @PathVariable Integer id) {
        int res = memberService.getBaseMapper().deleteById(id);
        if (res == 1) {
            return R.ok();
        } else {
            return R.error().message("删除成员信息失败！");
        }
    }

    @ApiOperation(tags = "updateMember", value = "后台管理修改成员信息接口")
    @PutMapping()
    public R<?> updateMember(@ApiParam(name = "member", value = "修改后的成员信息", required = true) TorchMember member) {
        int res = memberService.getBaseMapper().updateById(member);
        if (res == 1) {
            return R.ok();
        } else {
            return R.error().message("更新成员信息失败！");
        }
    }

    @ApiOperation(tags = "updateSelf", value = "成员个人修改个人信息接口")
    @PutMapping("/selfInfo")
    public R<?> updateSelf(@ApiParam(name = "selfInfo", value = "修改后的成员信息", required = true) Map<String, Object> selfInfo) {
        TorchMember member = new TorchMember();
        member.setNickname(selfInfo.get("nickname").toString());
        member.setBirthday(selfInfo.get("birthday").toString());
        member.setPhone(selfInfo.get("phone").toString());
        member.setPassword(selfInfo.get("password").toString());
        member.setAccountCode(selfInfo.get("accountCode").toString());
        UpdateWrapper<TorchMember> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", selfInfo.get("id"));
        int res = memberService.getBaseMapper().update(member, updateWrapper);
        if (res == 1) {
            return R.ok();
        } else {
            return R.error().message("更新成员信息失败！");
        }
    }

    @ApiOperation(tags = "getInfo", value = "获取成员个人信息接口")
    @GetMapping("/getInfo")
    public R<Object> getInfo(HttpServletRequest request) {
        Integer uid = new CookieUtils().getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").data(new TorchUserInfo());
        TorchMember member = memberService.getBaseMapper().selectById(uid);
        TorchUserInfo info = new TorchUserInfo(member);
        return R.ok().data(info);
    }

    @ApiOperation(tags = "getMemberList", value = "协会成员列表接口")
    @GetMapping("/getMemberList/{page}/{limit}")
    public R<Object> getMemberList(HttpServletRequest request,
                                   @ApiParam(name = "page", value = "1", required = true) @RequestParam(defaultValue = "1") @PathVariable Integer page,
                                   @ApiParam(name = "limit", value = "10", required = true) @RequestParam(defaultValue = "10") @PathVariable Integer limit) {
        Integer uid = new CookieUtils().getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").data(new TorchUserInfo());
        List<TorchMember> allTorchMember = memberService.getAllTorchMember(page, limit);
        return R.ok().data(allTorchMember);
    }

    @ApiOperation(tags = "getEventLog", value = "协会成员操作日志记录接口")
    @GetMapping("/getEventLog/{page}/{limit}")
    public R<Object> getEventLog(HttpServletRequest request,
                                 @ApiParam(name = "page", value = "1", required = true) @RequestParam(defaultValue = "1") @PathVariable Integer page,
                                 @ApiParam(name = "limit", value = "10", required = true) @RequestParam(defaultValue = "10") @PathVariable Integer limit) {
        Integer uid = new CookieUtils().getUidByCookie(request);
        if (uid == -1) return R.error().message("请先正确登录!").data(new TorchUserInfo());
        List<EventLog> eventLog = eventLogService.getEventLog(page, limit);
        return R.ok().data(eventLog);
    }

}
