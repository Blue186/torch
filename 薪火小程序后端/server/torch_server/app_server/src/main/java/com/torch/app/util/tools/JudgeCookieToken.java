package com.torch.app.util.tools;

import org.springframework.stereotype.Component;
import reactor.util.annotation.Nullable;

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


    /**
     * 判断用户请求的合法性
     * @param request 用户请求
     * @return true/false
     */
    public Boolean judge(HttpServletRequest request){
        userCookie = request.getHeader("c");
        if (userCookie==null){
            return false;
        }
//        判断user的cookie
        boolean exists = redisUtil.exists(userCookie);
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
