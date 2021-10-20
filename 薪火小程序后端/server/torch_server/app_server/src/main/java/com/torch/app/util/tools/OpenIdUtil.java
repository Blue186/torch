package com.torch.app.util.tools;

import net.sf.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

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
     * 通过 appid code secret 得到 openid
     * @param code wx.login 接口得到的 code
     * @param secret wx 密钥
     * @return openid
     */
    public String getOpenid(String code, String secret) {
        String appId = getAppId();
        BufferedReader in = null;
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
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

}
