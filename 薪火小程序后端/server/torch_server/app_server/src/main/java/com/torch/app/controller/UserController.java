package com.torch.app.controller;

import com.torch.app.entity.User;
import com.torch.app.service.UserService;
import com.torch.app.util.tools.OpenIdUtil;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     * @param userRegisterInfo 前端提交的用户注册信息
     * @return 状态
     */
    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    public R<?> registerUser(@ApiParam(name = "userRegisterInfo",value = "用户注册信息",required = true)Map<String,Object> userRegisterInfo){
        OpenIdUtil openIdUtil = new OpenIdUtil();
//        code 和 secret,相当于前端返回的数据要保护这个。。。
        String openid = openIdUtil.getOpenid(userRegisterInfo.get("code").toString(),userRegisterInfo.get("secret").toString());
        User user = new User();
        user.setOpenid(openid);
        user.setName(userRegisterInfo.get("name").toString());
        user.setPhone(userRegisterInfo.get("phone").toString());
        user.setEmail(userRegisterInfo.get("email").toString());
        user.setIsActive(1);
        user.setLevel(1);//用户权限，后续更改
        user.setAccount("111");//后续设置
        user.setPassword("111");//后续设置

        int res = userService.getBaseMapper().insert(user);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("用户注册失败");
        }
    }
    /**
     * 通过用户id获取用的的信息
     * @param id 用户id
     * @return user信息
     */
    @ApiOperation(value = "通过用户id获取用户基本信息")
    @GetMapping("/{id}")
    public R<?> getUser(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable Integer id){
        User user = userService.getBaseMapper().selectById(id);
        return R.ok().data("content", user);
    }

    /**
     * 用户修改个人信息
     * @param user 用户提交信息
     * @return 状态
     */
    @ApiOperation(value = "用户修改自己的个人信息")
    @PutMapping()
    public R<?> updateUser(@ApiParam(name = "user", value = "用户提交个人信息", required = true)@RequestBody User user){
        int res = userService.getBaseMapper().updateById(user);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("用户信息存入数据库失败");
        }
    }


}
