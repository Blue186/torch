package com.torch.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Article;
import com.torch.app.entity.vo.ArticleCon.PublishArticle;
import com.torch.app.entity.vo.ArticleCon.UpdateArticle;
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
import java.util.Date;
import java.util.List;


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
    public R<?> publishArticle(@ApiParam(name = "publishArticle",value = "用户文章信息",required = true) @RequestBody PublishArticle publishArt,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String[] urls = fileUtil.uploadImage(publishArt.getImages());

            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            Article article = new Article();
            article.setContent(publishArt.getContent());
            article.setAuthorId((Integer) uid);
            article.setCreateTime(new Date());
            article.setUpdateTime(new Date());
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
    @DeleteMapping("/{id}")
    public R<?> deleteArticle(@ApiParam(name = "id",value = "文章的id")@PathVariable Integer id,
                              HttpServletRequest request){
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
    public R<?> updateArticle(@ApiParam(name = "updateArt",value = "文章修改内容",required = true)@RequestBody UpdateArticle updateArt,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Article article = articleService.getBaseMapper().selectById(updateArt.getId());
            article.setContent(updateArt.getContent());
            String[] urls = fileUtil.uploadImage(updateArt.getImages());
            String join = StringUtils.join(urls, ";");
            article.setArtImage(join);
            article.setUpdateTime(new Date());
            int res = articleService.getBaseMapper().updateById(article);
            if (res==1){
                return R.ok().message("修改成功");
            }else {
                return R.error().message("修改失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "获取所用文章")
    @GetMapping("/{current}/{limit}")
    public R<?> getAllArticles(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Page<Article> page = new Page<>(current,limit);
            QueryWrapper<Article> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("update_time");
            articleService.page(page, wrapper);
            List<Article> records = page.getRecords();
            return R.ok().message("查询成功").data(records);
        }else {
            return R.error().code(-100);
        }
    }
    @ApiOperation(value = "获取一篇文章的内容")
    @GetMapping("/{id}")
    public R<?> getOneArticle(@ApiParam(name = "id",value = "文章id",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Article article = articleService.getBaseMapper().selectById(id);
            return R.ok().data(article);
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "点开文章后，浏览次数加一")
    @PutMapping("/viewNum")
    public R<?> viewNum(@ApiParam(name = "id",value = "文章的id",required = true) @RequestBody Integer id,
                        HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Article article = articleService.getBaseMapper().selectById(id);
            article.setViews(article.getViews()+1);
            int res = articleService.getBaseMapper().updateById(article);
            if (res==1){
                return R.ok().message("用户浏览文章加1");
            }else {
                return R.error().message("失败");
            }
        }else {
            return R.error().code(-100);
        }

    }
}


