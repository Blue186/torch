package com.torch.app.util.tools;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class JudgeCookieToken {

    private String userCookie = null;
    private String userToken;

    @Resource
    CookieUtils cookieUtils;

    /**
     * 获取用户请求中的cookie
     * @param request 用户请求
     * @return 用户的cookie
     */
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
     * 判断用户请求是否合法，验证cookie，token
     * @param cookie 用户cookie
     * @return
     */
    public Boolean judge(String cookie,Integer uid){

        String cookie_R = cookieUtils.getCookie(cookie);//redis中的cookie

        if (cookie_R!=null){
            if (cookie_R.equals(cookie)){
                TokenUtil tokenUtil = new TokenUtil();
                tokenUtil.getToken();

                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
