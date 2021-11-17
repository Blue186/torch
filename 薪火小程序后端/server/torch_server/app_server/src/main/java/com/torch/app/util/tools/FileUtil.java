package com.torch.app.util.tools;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Component
public class FileUtil {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    /**
     * 上传文件到image文件夹下
     * @param images 图片
     * @return 路径
     */
    public String[] uploadImage(MultipartFile[] images){
        String[] url = new String[images.length];
        String[] originalImageName = new String[images.length];
        for (int i=0;i<images.length;i++){
            originalImageName[i] = images[i].getOriginalFilename();

            String flag = IdUtil.fastSimpleUUID();

            String rootImagePath = System.getProperty("user.dir")+"/app_server/main/resource/images/"+flag+"_"+originalImageName[i];
            try {
                cn.hutool.core.io.FileUtil.writeBytes(images[i].getBytes(),rootImagePath);//把文件写入该路径
            } catch (IOException e) {
                e.printStackTrace();
            }
            url[i] = ip+":"+port+"/images/"+flag;
        }
        return url;
    }

    /**
     * 图片下载
     * @param flag uuid
     * @param response 响应
     */
    public void downloadImage(String flag, HttpServletResponse response){
        OutputStream os;
        String basePath = System.getProperty("user.dir")+"/app_server/src/main/resources/images/";//文件路径
        List<String> fileNames = cn.hutool.core.io.FileUtil.listFileNames((basePath));//获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到根参数一致的文件

        try {
            if (StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = cn.hutool.core.io.FileUtil.readBytes(basePath + fileName);//通过文件路径读取文字节流
                os = response.getOutputStream();//通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
