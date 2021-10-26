package com.torch.app.util.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

    // cookie 存在时间
    private static final int COOKIE_TIME = 60;

    // cookie 字符串长度
    private static final int COOKIE_LENGTH = 24;

    @Resource
    RedisUtil redisUtil;

    public  String randomStr() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < COOKIE_LENGTH; j++) {
            //生成一个97-122之间的int类型整数--为了生成小写字母
            int intValL = (int) (Math.random() * 26 + 97);
            //生成一个65-90之间的int类型整数--为了生成大写字母
            int intValU = (int) (Math.random() * 26 + 65);
            //生成一个30-39之间的int类型整数--为了生成数字
            int intValN = (int) (Math.random() * 10 + 48);

            int intVal = 0;
            int r = (int) (Math.random() * 3);

            if (r == 0) {
                intVal = intValL;
            } else if (r == 1) {
                intVal = intValU;
            } else {
                intVal = intValN;
            }

            sb.append((char) intVal);
        }
        return sb.toString();
    }

    /**
     * 设置 cookie
     * @param response 返回对象
     * @param uid 用户数据库 id
     */
    public String setCookie(HttpServletResponse response, Integer uid) {
        String c = randomStr();
        Cookie cookie = new Cookie("c", c);
        response.addCookie(cookie);
        redisUtil.set(c, uid, COOKIE_TIME);
        return c;
    }

    /**
     * 判断cookie是否存在，存在的话即是缓存时间内，这时候我们需要更新一次cookie？还是等token校验完再更新一次cookie和token？应该是后者
     * @param cookie cookie
     * @return 存在及返回cookie
     */
    public String getCookie(String cookie){
        boolean exists = redisUtil.exists(cookie);
        if (exists){
            return cookie;
        }else {
            return null;//再定夺
        }
    }
    /**
     * 通过 cookie 获取用户 id, 如果 cookie 过期或不存在则返回 -1
     * @param request 请求对象
     * @return 用户 id
     */
    public Integer getUidByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookie = "";
        for (Cookie c : cookies) {
            if (c.getName().equals("c")) {
                cookie = c.getValue();
                break;
            }
        }
        if (cookie.length() != 0) {
            return (Integer) redisUtil.get(cookie);
        } else {
            return -1;
        }
    }

}
