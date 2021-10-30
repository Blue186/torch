package com.torch.app.util.tools;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * author:A Handsome Cat
 */
@Component
public class JudgeCookieToken {

    private String userCookie = null;
    @Resource
    private RedisUtil redisUtil;

//    /**
//     * 获取用户请求中的cookie
//     * @param request 用户请求
//     * @return 用户的cookie
//     */
    public String getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("c")){
                    userCookie = cookie.getValue();
                }
            }
        }
        return userCookie;
    }
//
//    /**
//     * 判断用户请求是否合法，验证cookie，token
//     * @param cookie 用户cookie
//     * @return
//     */
//    public Boolean judge(String cookie){
//        boolean exists = redisUtil.exists(cookie);//判断cookie是否存在，存在及没有过期
//        if (exists){
////            如果未过期，且cookie正确，则拿到tk,openid
//            String openid = redisUtil.hmGet(cookie, "openid").toString();
//            String tk = redisUtil.hmGet(cookie, "tk").toString();
////            比较
//            TokenUtil tokenUtil = new TokenUtil();
//            String token = tokenUtil.generateToken(cookie, openid);
//            return tk.equals(token);
//        }else {
//            return false;
//        }
//    }

    /**
     * 判断用户请求的合法性
     * @param request 用户请求
     * @return true/false
     */
    public Boolean judge(HttpServletRequest request){
//        拿到user的cookie
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("c")){
                    userCookie = cookie.getValue();
                }
            }
        }
//        判断user的cookie
        boolean exists = redisUtil.exists(userCookie);//判断cookie是否存在，存在及没有过期
        if (exists){
//            如果未过期，且cookie正确，则拿到tk,openid
            String openid = redisUtil.hmGet(userCookie, "openid").toString();
            String tk = redisUtil.hmGet(userCookie, "tk").toString();
//            比较
            TokenUtil tokenUtil = new TokenUtil();
            String token = tokenUtil.generateToken(userCookie, openid);
            return tk.equals(token);
        }else {
            return false;
        }
    }
}
