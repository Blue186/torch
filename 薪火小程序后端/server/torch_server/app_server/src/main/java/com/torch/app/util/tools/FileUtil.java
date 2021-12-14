package com.torch.app.util.tools;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Component
public class FileUtil {
    @Value("${server.port}")
    private String port;
    private static final String ip = "http://139.186.170.118";

    public String uploadImage(String base64Str){
        //            这里要先截取一下图片原编码和拿到图片后缀
        String prefix = "";
        String data = "";//实体部分数据
        if(base64Str==null||"".equals(base64Str)){
            return "0";//上传失败，上传图片数据为空
        }else {
            String [] d = base64Str.split("base64,");//将字符串分成数组
            if(d != null && d.length == 2){
                prefix = d[0];
                data = d[1];
            }else {
                return "-1";//上传失败，数据不合法
            }
        }

        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(prefix)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(prefix)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(prefix)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(prefix)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return "-2";//上传图片格式不合法,支持jpg,ico,png,gif
        }

        String flag = IdUtil.fastSimpleUUID();//生成随记UUID码
        String imageName = flag+suffix;
//        String imageFilePath = System.getProperty("user.dir")+"/torch_server/app_server/src/main/resources/static/images/"+imageName;
        String imageFilePath = System.getProperty("user.dir")+"/static/images/"+imageName;
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = base64Decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imageFilePath);
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip+":"+port+"/static/images/" +imageName;
//        return ip+":"+port+"/static/images/" +imageName;
    }

}
