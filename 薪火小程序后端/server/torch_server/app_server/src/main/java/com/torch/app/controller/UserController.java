package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.UserLogin;
import com.torch.app.service.UserService;
import com.torch.app.util.tools.*;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = {"用户登录注册相关接口"},value = "用户登录注册相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户登录注册接口")
    @PostMapping("/login")
    public R<?> loginUser(@ApiParam(value = "用户登录信息",name = "userLogin",required = true) @RequestBody UserLogin userLogin,
                          HttpServletResponse response){
//        先获取openid
        OpenIdUtil openIdUtil = new OpenIdUtil();
        String openId = openIdUtil.getOpenid(userLogin.getCode());
//        通过openid获取用户的id
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        User user = userService.getBaseMapper().selectOne(queryWrapper);
        if (user==null){
            User newUser = new User();
            newUser.setOpenid(openId);
            newUser.setIsActive(1);
            userService.getBaseMapper().insert(newUser);
            return R.ok().message("注册成功");
        }else {
//            这里登录成功后，我们返回一个cookie和token用于校验用户登录安全，以后每次用户请求接口时，都需要将cookie和token携带上
            CookieUtils cookieUtils = new CookieUtils();
            String cookie = cookieUtils.setCookie(response, user.getId());//登录时返回给response的cookie
//            openIdUtil.setOpenid(user.getId(),openId);//设置openid在Redis中

            TokenUtil tokenUtil = new TokenUtil();
            String token = tokenUtil.generateToken(cookie, openId);

            Map<String,Object> map = new HashMap<>();
            map.put("openid",openId);
            map.put("tk",token);
            map.put("uid",user.getId());
            redisUtil.hmSet(cookie,map);//完成cookie、openid和token的缓存填入
//            tokenUtil.setToken(token, user.getId());//设置token在Redis中
            return R.ok().message("登录成功").data("c",cookie);
        }
    }

    /**
     * 通过用户id获取用的的信息
     * @param id 用户id
     * @return user信息
     */
    @ApiOperation(value = "通过用户id获取用户基本信息")
    @GetMapping("/{id}")
    public R<?> getUser(@ApiParam(name = "id", value = "用户id", required = true) @PathVariable Integer id,
                        HttpServletRequest request) {
//        获取用户请求中的cookie，并进行校验，可以封装成一个工具类。
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);//判断请求是否合法
        if (judge){
            User user = userService.getBaseMapper().selectById(id);
            return R.ok().data("content", user);
        }else {
            return R.error().data("login_error",-100);//这里有误的情况下就要进行重新登录操作，我返回一个login_error,-100进行判断
        }


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
