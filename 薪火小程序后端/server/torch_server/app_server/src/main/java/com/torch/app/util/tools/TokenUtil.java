package com.torch.app.util.tools;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 生成 token 及验证 token 的工具类
 */
public class TokenUtil {

    /**
     * 生成 token
     * @param cookie redis 中的 cookie
     * @param openid redis 中根据 cookie 可以获取到的 openid
     * @param time 登录时间
     * @return token
     */
    public String generateToken(String cookie, String openid, String time) {
        String mix = cookie + openid + time;
        String md5 = DigestUtils.md5DigestAsHex(mix.getBytes(StandardCharsets.UTF_8));
        return md5;
    }

}
