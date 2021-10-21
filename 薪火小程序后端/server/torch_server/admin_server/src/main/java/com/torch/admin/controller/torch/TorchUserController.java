package com.torch.admin.controller.torch;

import com.torch.admin.entity.torch.vo.TorchUserLogin;
import com.torch.admin.entity.torch.vo.TorchUserRegister;
import com.torch.admin.service.torch.TorchMemberService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "TorchUserController", value = "登录注册修改密码相关接口")
@RestController
@RequestMapping("/admin/user")
public class TorchUserController {

    @Resource
    private TorchMemberService memberService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R<?> login(@RequestBody(required = true) TorchUserLogin torchUser) {
        // 获取用户id
        Integer id = memberService.getIdByAccountCodeAndPassword(torchUser.getAccount(), torchUser.getPassword());
        if (id == -1) return R.error().data("message", "账号或者密码错误");
        else if (id == -2) return R.error().data("message", "提交内容格式出错");
        // 根据id得到权限
        return R.ok().data("message", "登录成功");
    }

    @ApiOperation("添加新成员接口")
    @PostMapping("/register")
    public R<?> register(@RequestBody(required = true) TorchUserRegister register) {
        memberService.addTorchMember(register);
        return R.ok().data("message", "添加成功");
    }

}
