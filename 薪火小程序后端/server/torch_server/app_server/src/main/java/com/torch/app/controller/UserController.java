package com.torch.app.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.User;
import com.torch.app.entity.vo.UserCon.CodeCheck;
import com.torch.app.entity.vo.UserCon.UserInfo;
import com.torch.app.entity.vo.UserCon.UserLogin;
import com.torch.app.service.UserService;
import com.torch.app.util.commonutils.CacheCode;
import com.torch.app.util.tools.*;
import com.torch.app.util.commonutils.R;
import com.torch.app.util.commonutils.ResultCode;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Api(tags = {"用户登录注册相关接口"},value = "用户登录注册相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private RedisUtil redisUtil;

    private TokenUtil tokenUtil;

    private JudgeCookieToken judgeCookieToken;

    private EmailSendUtil emailSendUtil;

    private CookieUtils cookieUtils;

    private OpenIdUtil openIdUtil;

    private RedissonClient redissonClient;

    @Value("${spring.mail.username}")
    private String MAIL;

    @Autowired
    public UserController(UserService userService,
                          RedisUtil redisUtil,
                          TokenUtil tokenUtil,
                          JudgeCookieToken judgeCookieToken,
                          EmailSendUtil emailSendUtil,
                          CookieUtils cookieUtils,
                          OpenIdUtil openIdUtil,
                          RedissonClient redissonClient) {
        this.userService = userService;
        this.redisUtil = redisUtil;
        this.tokenUtil = tokenUtil;
        this.judgeCookieToken = judgeCookieToken;
        this.emailSendUtil = emailSendUtil;
        this.cookieUtils = cookieUtils;
        this.openIdUtil = openIdUtil;
        this.redissonClient = redissonClient;
    }

    @ApiOperation(value = "用户登录注册接口")
    @PostMapping("/login")
    public R<?> loginUser(@ApiParam(value = "用户登录信息",name = "userLogin",required = true) @RequestBody UserLogin userLogin,
                          HttpServletResponse response){
        String openId = openIdUtil.getOpenid(userLogin.getCode());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openId);
        User user = userService.getBaseMapper().selectOne(queryWrapper);
        String cookie = cookieUtils.setCookie(response);
        String token = tokenUtil.generateToken(cookie, openId);
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();
        map.put("openid",openId);
        map.put("tk",token);
        if (user==null){
            User newUser = new User();
            newUser.setOpenid(openId);
            newUser.setIsActive(0);
            userService.getBaseMapper().insert(newUser);
            //这里应该直接就能通过newUser拿到id
            map.put("uid",newUser.getId());
            redisUtil.hmSet(cookie,map);//完成cookie、openid和token的缓存填入
            Map<String,Object> map_R = new HashMap<>();
            map_R.put("c",cookie);
            map_R.put("status",true);
            log.info("新用户注册:{}",newUser.getId());
            return R.ok().message("注册成功").data(map_R);
        }else {
//            这里登录成功后，我们返回一个cookie和token用于校验用户登录安全，以后每次用户请求接口时，都需要将cookie和token携带上
            map.put("uid",user.getId());
            redisUtil.hmSet(cookie,map);//完成cookie、openid和token的缓存填入
            Map<String,Object> map_R = new HashMap<>();
            map_R.put("c",cookie);
            map_R.put("status",false);
            log.info("用户登录成功：{}",user.getId());
            return R.ok().message("登录成功").data(map_R);
        }
    }

    /**
     * 通过用户id获取用的的信息
     * @return user信息
     */
    @LogCostTime
    @ApiOperation(value = "通过用户id获取用户基本信息")
    @GetMapping()
    public R<?> getUser(@ApiParam(name = "request",value = "请求携带cookie即可") HttpServletRequest request) {
//        获取用户请求中的cookie，并进行校验，可以封装成一个工具类。
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");//这里获取uid的过程依然用redisUtil来完成，方便一些，都是获取redis中的数据嘛
        String key = CacheCode.CACHE_USER+uid;
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        if (!bloomFilter.contains(key)){//如果缓存中有用户信息就通过缓存来返回
            return R.error().setErrorCode(ResultCode.wrongMsg);
        }
        User user = (User)redissonClient.getBucket(key).get();
        if (user==null){
            log.info("从数据库中拿到用户信息，并更新缓存");
            user = userService.getBaseMapper().selectById(uid.toString());
            redissonClient.getBucket(key).set(user);
        }
        log.info("从缓存中拿到用户信息");
        return R.ok().data(user);
    }

    /**
     * 用户修改个人信息
     * @return 状态
     */
    @LogCostTime
    @ApiOperation(value = "用户修改自己的个人信息")
    @PutMapping()
    public R<?> updateUser(@ApiParam(name = "user", value = "用户提交个人信息", required = true)@RequestBody UserInfo userInfo,
                           HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        User user = userService.setUserInfo((Integer) redisUtil.hmGet(cookie, "uid"), userInfo);
        log.info("user:{}",userInfo);
        int res = userService.getBaseMapper().updateById(user);
        if (res==1){
            String key = CacheCode.CACHE_USER+user.getId();
            redissonClient.getBucket(key).set(user);

            log.info("用户修改个人信息成功,OK?");
            return R.ok();
        }else {
            log.error("用户修改数据失败");
            return R.error().message("用户信息存入数据库失败");
        }
    }

    /**
     * 发送邮箱验证码
     * @param mail 邮箱
     * @param request 请求
     * @return
     */
    @LogCostTime
    @ApiOperation(value = "发送邮箱验证码")
    @PostMapping("/mailVerify")
    public R<?> mailVerify(@ApiParam(name = "mail",value = "邮箱",required = true)@RequestBody String mail,
                           HttpServletRequest request){
        JSONObject jsonObject = new JSONObject(mail);
        String userMail = jsonObject.getStr("mail");
        String cookie = judgeCookieToken.getCookie(request);
        emailSendUtil.sendMailVerify(MAIL, userMail,cookie);
        log.info("邮箱验证码发送成功");
        return R.ok().message("验证码成功发送");
    }

    /**
     * 邮箱验证码校验
     * @param request 请求
     * @return
     */
    @LogCostTime
    @ApiOperation(value = "邮箱验证码校验")
    @PostMapping("/codeCheck")
    public R<?> codeCheck(@ApiParam(name = "codeCheck",value = "邮箱校验类") @RequestBody CodeCheck codeCheck,
                          HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        String md5_R = redisUtil.get(codeCheck.getMail()).toString();
        String md5 = tokenUtil.generateMd5(codeCheck.getMail(), cookie, codeCheck.getCode());
        if (md5_R==null){
            log.error("验证码超时");
            return R.error().setErrorCode(ResultCode.timeOut);
        }else {
            if (md5.equals(md5_R)){
                String uid = redisUtil.hmGet(cookie, "uid").toString();
                String key = CacheCode.CACHE_USER+uid;
                RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
                if (bloomFilter.contains(key)){
                    User user = (User) redissonClient.getBucket(key).get();
                    user.setEmail(codeCheck.getMail());
                }
                User user = userService.getBaseMapper().selectById(uid);
                user.setEmail(codeCheck.getMail());
                int res = userService.getBaseMapper().updateById(user);
                if (res==1){
                    //缓存更新
                    bloomFilter.add(key);
                    redissonClient.getBucket(key).set(user);
                    log.info("用户邮箱更新成功");
                    return R.ok().message("用户邮箱更新成功");
                }else {
                    log.error("用户邮箱更新失败");
                    return R.error().message("用户邮箱更新失败");
                }
            }else {
                log.error("验证码错误");
                return R.error().message("邮箱验证码错误");
            }
        }
    }


    @GetMapping("/info")
    public R<?> getBoxInfo(){
        String boxInfo = "{\"school\":{\"content\":\"喜好1\",\"placeholder\":\"\"},\"name\":{\"content\":\"备用昵称1\",\"placeholder\":\"\"},\"nickName\":{\"content\":\"备用昵称2\",\"placeholder\":\"\"},\"grade\":{\"content\":\"喜好2\",\"placeholder\":\"\"},\"qq\":{\"content\":\"喜好3\",\"placeholder\":\"\"},\"phone\":{\"content\":\"喜好4\",\"placeholder\":\"\"},\"volAccount\":{\"content\":\"1+1\",\"placeholder\":\"\"},\"verificationCode\":{\"content\":\"1+2\",\"placeholder\":\"\"},\"email\":{\"content\":\"1+3\",\"placeholder\":\"\"},\"button\":{\"code\":\"\",\"confirm\":\"\"},\"selfIntroduction\":{\"content\":\"特长\",\"placeholder\":\"\"}}";
        JSONObject jsonObject = new JSONObject(boxInfo);
        return R.ok().data(jsonObject);
    }
}
