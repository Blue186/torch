package com.torch.app.controller;

import com.torch.app.util.tools.FileUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags = {"文件上传相关接口"},value = "文件上传相关接口")
@RestController
@RequestMapping("/file")
public class FileController {

    private FileUtil fileUtil;


    @Autowired
    public FileController(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    //    这里就是上传图片，返回图片地址的接口
    @LogCostTime
    @ApiOperation(value = "上传图片接口")
    @PostMapping("/images")
    public R<?> uploadImages(@RequestBody @ApiParam(value = "图片的base64数组",name = "base64Str") String[] base64Str){
        if (base64Str==null){
            return R.error().message("图片为空");
        }
        List<String> urls = new ArrayList<>();
        for (String s : base64Str) {
            String url = fileUtil.uploadImage(s);
            if (url.equals("0")){
                log.error("上传失败，上传图片数据为空");
                return R.error().message("上传失败，上传图片数据为空");
            }
            if (url.equals("-1")){
                log.error("上传失败，数据不合法");
                return R.error().message("上传失败，数据不合法");
            }
            if (url.equals("-2")){
                log.error("上传图片格式不合法,支持jpg,ico,png,gif");
                return R.error().message("上传图片格式不合法,支持jpg,ico,png,gif");
            }
            urls.add(url);
        }
        return R.ok().data(urls);
    }
}
