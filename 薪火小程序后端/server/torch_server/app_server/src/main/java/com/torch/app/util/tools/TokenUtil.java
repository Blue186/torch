package com.torch.app.util.tools;

import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 生成 token 及验证 token 的工具类
 */
public class TokenUtil {
    private static final int TOKEN_TIME = 60;
    @Resource
    private RedisUtil redisUtil;
    /**
     * 生成 token
     * @param cookie redis 中的 cookie
     * @param openid redis 中根据 cookie 可以获取到的 openid
     * @return token
     */
    public String generateToken(String cookie, String openid) {
//        这里token是否需要存在Redis中？不然怎么进行校验？但是cookie已经在Redis中设置了时间过期，
//        主要是这边需要对用户请求操作进行校验，判断cookie+openid的MD5加密是否一致。
        String mix = cookie + openid;
        String md5 = DigestUtils.md5DigestAsHex(mix.getBytes(StandardCharsets.UTF_8));
        return md5;
    }

    public String generateMd5(String mail,String cookie,String code){
        String mix = mail+cookie+code;
        String md5 = DigestUtils.md5DigestAsHex(mix.getBytes(StandardCharsets.UTF_8));
        return md5;
    }

}
