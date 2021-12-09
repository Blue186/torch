package com.torch.admin.controller.torch;

import com.torch.admin.entity.torch.vo.TorchUserLogin;
import com.torch.admin.entity.torch.vo.TorchUserRegister;
import com.torch.admin.service.torch.*;
import com.torch.admin.utils.CookieUtils;
import com.torch.admin.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Api(tags = {"薪火管理系统用户登录注册修改密码相关接口"}, value = "登录注册修改密码相关接口")
@RestController
@RequestMapping("/admin/user")
public class TorchUserController {

    @Resource
    private TorchMemberService memberService;

    @Resource
    private TorchPublishLevelService torchPublishLevelService;

    @Resource
    private TorchSuggestLevelService torchSuggestLevelService;

    @Resource
    private TorchBirthLevelService torchBirthLevelService;

    @Resource
    private TorchPowerService torchPowerService;

    @Resource
    private CookieUtils cookieUtils;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R<?> login(@RequestBody(required = true) TorchUserLogin torchUser, HttpServletResponse response) {
        System.out.println("登录");
        // 获取用户id
        Integer id = memberService.getIdByAccountCodeAndPassword(torchUser.getAccount(), torchUser.getPassword());
        if (id == -1) return R.error().message("账号或者密码错误");
        else if (id == -2) return R.error().message("提交内容格式出错");
        // 根据id得到权限
        String cookie = cookieUtils.setCookie(response, id);
        HashMap<String, String> c = new HashMap<>();
        c.put("c", cookie);
        return R.ok().message("登录成功!").data(c);
    }

    @ApiOperation("添加新成员接口")
    @PostMapping("/register")
    public R<?> register(@RequestBody(required = true) TorchUserRegister register) {
        Integer uid = memberService.addTorchMember(register, String.valueOf(new Date().getTime()));
        if (uid == null) return R.error().message("成员添加失败!");
        Integer publishLevelId = torchPublishLevelService.add(1, 1, uid);
        Integer suggestLevelId = torchSuggestLevelService.add(1, uid);
        Integer birthLevelId = torchBirthLevelService.add(1, uid);
        Integer powerId = torchPowerService.add(uid, publishLevelId, suggestLevelId, birthLevelId);
        if (powerId == null) return R.error().message("权限信息初始化失败!");
        return R.ok().message("成员添加成功!");
    }

}
