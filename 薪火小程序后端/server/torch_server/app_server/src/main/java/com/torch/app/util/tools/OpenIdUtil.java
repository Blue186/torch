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

//    @Value("${wx.secret}")
    private String secret = "ad527c3572bd15f95a47df54aa47e25c";
//    @Value("${wx.appid}")
    private String appid = "wx340fc38eece916c2";
    /**
     * 读取 resources 下 application 的 wx-appid
     * @return appId
     */
//    private String getAppId() {
//        Yaml yaml = new Yaml();
//        InputStream in = OpenIdUtil.class.getClassLoader().getResourceAsStream("../resources/application.yaml");
//        Map<String, Object> map = yaml.loadAs(in, Map.class);
//        String appId = map.getOrDefault("wx-appid", "").toString();
//        System.out.println(appId);
//        return appId;
//    }

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
