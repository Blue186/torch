package com.torch.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Article;
import com.torch.app.entity.vo.ArticleCon.ArticleInfo;
import com.torch.app.entity.vo.ArticleCon.PublishArticle;
import com.torch.app.entity.vo.ArticleCon.UpdateArticle;
import com.torch.app.service.ArtImagesService;
import com.torch.app.service.ArticleService;
import com.torch.app.util.tools.FileUtil;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
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
    @Resource
    private ArtImagesService artImagesService;

/**
 * 薪火推文在管理端接口，此处为用户所发文章相应接口
 */
    @ApiOperation(value = "用户发布文章接口")
    @PostMapping()
    public R<?> publishArticle(@ApiParam(name = "publishArticle",value = "用户文章信息",required = true) @RequestBody PublishArticle publishArt,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        Article article = articleService.setPublishArt((Integer) uid, publishArt);
        int insert = articleService.getBaseMapper().insert(article);
//            插入图片
        articleService.insertImages(publishArt.getImages(), article.getId());
        if (insert==1){
            return R.ok().message("用户文章发布成功");
        }else {
            return R.error().message("用户文章发布失败");
        }

    }

    @ApiOperation(value = "用户删除已发布的文章接口")
    @DeleteMapping("/{id}")
    public R<?> deleteArticle(@ApiParam(name = "id",value = "文章的id")@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        int res = articleService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok().message("文章删除成功");
        }else {
            return R.error().message("文章删除失败");
        }
    }

    @ApiOperation(value = "用户修改")
    @PutMapping()
    public R<?> updateArticle(@ApiParam(name = "updateArt",value = "文章修改内容",required = true)@RequestBody UpdateArticle updateArt,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge) {
            return R.error().code(-100);
        }
        Article article = articleService.getBaseMapper().selectById(updateArt.getId());
        article.setContent(updateArt.getContent());
        article.setUpdateTime(new Date());
        int res = articleService.getBaseMapper().updateById(article);
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        articleService.updateImages(updateArt.getImages(), (Integer) uid);
        if (res==1){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }

    @ApiOperation(value = "获取所用文章")
    @GetMapping("/{current}/{limit}")
    public R<?> getAllArticles(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        Page<Article> page = new Page<>(current,limit);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        articleService.page(page, wrapper);
        List<Article> records = page.getRecords();
//        上面只是文章内容，没有图片，这里我必须加上去
        List<ArticleInfo> articleInfos = articleService.getArticleInfos(records);
        return R.ok().message("查询成功").data(articleInfos);
    }
    @ApiOperation(value = "获取一篇文章的内容")
    @GetMapping("/{id}")
    public R<?> getOneArticle(@ApiParam(name = "id",value = "文章id",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        Article article = articleService.getBaseMapper().selectById(id);
        ArticleInfo articleInfo = articleService.getArticleInfos(article);
        return R.ok().data(articleInfo);
    }

    @ApiOperation(value = "点开文章后，浏览次数加一")
    @PutMapping("/viewNum")
    public R<?> viewNum(@ApiParam(name = "id",value = "文章的id",required = true) @RequestBody Integer id,
                        HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }

        Article article = articleService.getBaseMapper().selectById(id);
        article.setViews(article.getViews()+1);
        int res = articleService.getBaseMapper().updateById(article);
        if (res==1){
            return R.ok().message("用户浏览文章加1");
        }else {
            return R.error().message("失败");
        }
    }
}


