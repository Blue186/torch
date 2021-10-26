package com.torch.app.util.tools;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * 处理获取用户 openid 的 util
 */
public class OpenIdUtil {
    // openid 存在时间
    private static final int OPENID_TIME = 60;

    @Value("${wx-secret}")
    private String secret;
    @Resource
    RedisUtil redisUtil;
    /**
     * 读取 resources 下 application 的 wx-appid
     * @return appId
     */
    private String getAppId() {
        Yaml yaml = new Yaml();
        InputStream in = OpenIdUtil.class.getClassLoader().getResourceAsStream("../resources/application.yaml");
        Map<String, Object> map = yaml.loadAs(in, Map.class);
        String appId = map.getOrDefault("wx-appid", "").toString();
        System.out.println(appId);
        return appId;
    }

    /**
     * 读取 resources 下 application 的 wx-secret
     * @return secret
     */
//    private String getSecret(){
//        Yaml yaml = new Yaml();
//        InputStream in = OpenIdUtil.class.getClassLoader().getResourceAsStream("../resources/application.yaml");
//        Map<String,Object> map = yaml.loadAs(in,Map.class);
//        return map.getOrDefault("wx-appid","").toString();
//    }
    /**
     * 通过 appid code secret 得到 openid
     * @param code wx.login 接口得到的 code
     * @return openid
     */
    public String getOpenid(String code) {
        String appId = getAppId();
        BufferedReader in = null;
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        System.out.println("secret:-----------------------------"+secret);
        try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            String jsonStr = sb.toString();
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            return jsonObject.getString("openid");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 设置 openid
     * @param openid 用户的openid
     * @param uid 用户编号
     */
    public void setOpenid(Integer uid,String openid){
        redisUtil.set(uid.toString(),openid,OPENID_TIME);
    }

    public String getOpenidByUid(Integer uid){
        boolean exists = redisUtil.exists(uid.toString());
        if (exists){
            return (String) redisUtil.get(uid.toString());

        }else{
            return null;//再定夺
        }
    }

}
