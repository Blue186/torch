package com.torch.app.controller;


import com.torch.app.entity.Article;
import com.torch.app.service.ArticleService;
import com.torch.app.util.tools.FileUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;


@Api(tags = {"用户文章发布相关接口"},value = "用户文章发布相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private JudgeCookieToken judgeCookieToken;
    @Resource
    private FileUtil fileUtil;

/**
 * 薪火推文在管理端接口，此处为用户所发文章相应接口
 */

    @ApiOperation(value = "用户发布文章接口")
    @PostMapping()
    public R<?> publishArticle(@ApiParam(name = "article",value = "用户文章信息",required = true) @RequestParam String content,
                               @RequestParam MultipartFile[] images, HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            final String[] urls = fileUtil.uploadImage(images);

            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            Article article = new Article();
            article.setContent(content);
            article.setAuthorId((Integer) uid);
            article.setCreateTime(new Date());
            article.setThumbsUp(0);
            article.setViews(0);
//            用":"分割图片的路径。
            String join = StringUtils.join(urls, ";");
            article.setArtImage(join);
            int insert = articleService.getBaseMapper().insert(article);
            if (insert==1){
                return R.ok().message("用户文章发布成功");
            }else {
                return R.error().message("用户文章发布失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "用户删除已发布的文章接口")
    @DeleteMapping()
    public R<?> deleteArticle(@ApiParam(name = "id",value = "文章的id")@RequestParam Integer id, HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            int res = articleService.getBaseMapper().deleteById(id);
            if (res==1){
                return R.ok().message("文章删除成功");
            }else {
                return R.error().message("文章删除失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "用户修改")
    @PutMapping()
    public R<?> updateArticle(@ApiParam(name = "id",value = "文章id",required = true)@RequestParam Integer id, HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){

            return R.ok().message("");
        }else {
            return R.error().code(-100);
        }
    }
}


