package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.UserInfo;
import com.torch.app.entity.vo.UserLogin;
import com.torch.app.service.UserService;
import com.torch.app.util.tools.*;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
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
    @Resource
    private TokenUtil tokenUtil;

    @ApiOperation(value = "用户登录注册接口")
    @PostMapping("/login")
    public R<?> loginUser(@ApiParam(value = "用户登录信息",name = "userLogin",required = true) @RequestBody UserLogin userLogin,
                          HttpServletResponse response){
//        先获取openid
        OpenIdUtil openIdUtil = new OpenIdUtil();
        String openId = openIdUtil.getOpenid(userLogin.getCode());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        User user = userService.getBaseMapper().selectOne(queryWrapper);

        CookieUtils cookieUtils = new CookieUtils();
        String cookie = cookieUtils.setCookie(response);
        TokenUtil tokenUtil = new TokenUtil();
        String token = tokenUtil.generateToken(cookie, openId);
        Map<String,Object> map = new HashMap<>();
        map.put("openid",openId);
        map.put("tk",token);

        if (user==null){
            User newUser = new User();
            newUser.setOpenid(openId);
            newUser.setIsActive(1);
            int res = userService.getBaseMapper().insert(newUser);

            if (res==1){
                User user_N = userService.getBaseMapper().selectOne(queryWrapper);
                map.put("uid",user_N.getId());
                redisUtil.hmSet(cookie,map);//完成cookie、openid和token的缓存填入
//            tokenUtil.setToken(token, user.getId());//设置token在Redis中
                System.out.println(redisUtil.hmGet(cookie,"openid").toString());;
            }
            Map<String,Object> map_R = new HashMap<>();
            map_R.put("c",cookie);
            map_R.put("status",true);
            return R.ok().message("注册成功").data(map_R);
        }else {
//            这里登录成功后，我们返回一个cookie和token用于校验用户登录安全，以后每次用户请求接口时，都需要将cookie和token携带上
            map.put("uid",user.getId());
            redisUtil.hmSet(cookie,map);//完成cookie、openid和token的缓存填入
//            tokenUtil.setToken(token, user.getId());//设置token在Redis中
            System.out.println(redisUtil.hmGet(cookie,"openid").toString());;
            Map<String,Object> map_R = new HashMap<>();
            map_R.put("c",cookie);
            map_R.put("status",false);
            return R.ok().message("登录成功").data(map_R);
        }
    }

    /**
     * 通过用户id获取用的的信息
     * @return user信息
     */
    @ApiOperation(value = "通过用户id获取用户基本信息")
    @GetMapping()
    public R<?> getUser(@ApiParam(name = "request",value = "请求携带cookie即可") HttpServletRequest request) {
//        获取用户请求中的cookie，并进行校验，可以封装成一个工具类。
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);//判断请求是否合法
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            User user = userService.getBaseMapper().selectById(uid.toString());
            return R.ok().data(user);
        }else {
            return R.error().code(-100);//这里有误的情况下就要进行重新登录操作，我返回一个login_error,-100进行判断
        }
    }

    /**
     * 用户修改个人信息
     * @return 状态
     */
    @ApiOperation(value = "用户修改自己的个人信息")
    @PutMapping()
    public R<?> updateUser(@ApiParam(name = "user", value = "用户提交个人信息", required = true)@RequestBody UserInfo userInfo,
                           HttpServletRequest request){
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            User user = userService.getBaseMapper().selectById((Serializable) redisUtil.hmGet(cookie, "uid"));
            user.setName(userInfo.getName());
            user.setPhone(userInfo.getPhone());
            user.setQq(userInfo.getQq());
            user.setSchool(userInfo.getSchool());
            user.setGrade(userInfo.getGrade());
            user.setVolAccount(user.getVolAccount());
            user.setNickName(userInfo.getNickName());
            user.setAvatarImage(userInfo.getAvatarImage());
            int res = userService.getBaseMapper().updateById(user);
            if (res==1){
                return R.ok();
            }else {
                return R.error().message("用户信息存入数据库失败");
            }
        }else {
            return R.error().code(-100);
        }

    }

    /**
     * 发送邮箱验证码
     * @param mail 邮箱
     * @param request 请求
     * @return
     */
    @ApiOperation(value = "发送邮箱验证码")
    @PostMapping("/mailVerify")
    public R<?> mailVerify(@ApiParam(name = "",value = "",required = true) String mail,
                           HttpServletRequest request){
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);

            EmailSendUtil emailSendUtil = new EmailSendUtil();
            emailSendUtil.sendMailVerify("3057179865", mail,cookie);

            return R.ok().message("验证码成功发送");
        }else {
            return R.error().code(-100);
        }
    }

    /**
     * 邮箱验证码校验
     * @param request 请求
     * @return
     */
    @ApiOperation(value = "邮箱验证码校验")
    @PostMapping("/codeCheck")
    public R<?> codeCheck(@ApiParam(name = "mail",value = "邮箱",required = true)String mail,
                          @ApiParam(name = "code",value = "邮箱验证码",required = true) String code,HttpServletRequest request){
        JudgeCookieToken judgeCookieToken = new JudgeCookieToken();
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            String md5_R = redisUtil.get(mail).toString();
            String md5 = tokenUtil.generateMd5(mail, cookie,code);
            if (md5_R==null){
                Map<String,Object> map = new HashMap<>();
                map.put("timeout",40);
                return R.error().data(map);
            }else {
                if (md5.equals(md5_R)){
                    String uid = redisUtil.hmGet(cookie, "uid").toString();
                    User user = userService.getBaseMapper().selectById(uid);
                    user.setEmail(mail);
                    int res = userService.getBaseMapper().updateById(user);
                    if (res==1){
                        return R.ok().message("用户邮箱更新成功");
                    }else {
                        return R.error().message("用户邮箱更新失败");
                    }
                }else {
                    return R.error().message("邮箱验证码错误");
                }
            }
        }else {
            return R.error().code(-100);
        }
    }

}
