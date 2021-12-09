package com.torch.app.util.tools;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 * 处理获取用户 openid 的 util
 */
@Component
public class OpenIdUtil {

    private String secret = "ad527c3572bd15f95a47df54aa47e25c";
    private String appid = "wx340fc38eece916c2";

    public  String getOpenid(String code) {
        BufferedReader in = null;
        //appid和secret是开发者分别是小程序ID和小程序密钥，开发者通过微信公众平台-》设置-》开发设置就可以直接获取，
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="
                +appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
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
            System.out.println("code:-------"+code);
            System.out.println("appid:-----------"+appid);
            System.out.println("secret:-----------"+secret);
            System.out.println("openid:-----------"+sb.toString());
            JSONObject jsonObject = JSONObject.fromObject(sb.toString());
            Object openid = jsonObject.get("openid");
            System.out.println("openid---------------"+openid.toString());
            return openid.toString();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }


}
