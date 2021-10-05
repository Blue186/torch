package com.torch.admin.controller.torch;

import com.torch.admin.entity.torch.vo.TorchUser;
import com.torch.admin.service.torch.TorchMemberService;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "TorchUserController", value = "登录注册修改密码相关接口")
@RestController
@RequestMapping("/admin/user")
public class TorchUserController {

    @Autowired
    private TorchMemberService memberService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R login(@RequestBody(required = true) TorchUser torchUser) {
        // 获取用户id
        Integer id = memberService.getIdByAccountCodeAndPassword(torchUser.getAccount(), torchUser.getPassword());
        if (id == -1) return R.error().data("message", "账号或者密码错误");
        else if (id == -2) return R.error().data("message", "提交内容格式出错");
        // 根据id得到权限
        return R.ok().data("message", "登录成功");
    }

}
