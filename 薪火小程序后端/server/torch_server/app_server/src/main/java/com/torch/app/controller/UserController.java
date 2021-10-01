package com.torch.app.controller;

import com.torch.app.entity.User;
import com.torch.app.service.UserService;
import commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过用户id获取用的的信息
     * @param id 用户id
     * @return user信息
     */
    @ApiOperation(value = "通过用户id获取用户基本信息")
    @GetMapping("/{id}")
    public R getUser(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable Integer id){
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
    public R updateUser(@ApiParam(name = "user", value = "用户提交个人信息", required = true)@RequestBody User user){
        int res = userService.getBaseMapper().updateById(user);
        if (res==1){
            return R.ok();
        }else {
            return R.error().message("用户信息存入数据库失败");
        }
    }


}
