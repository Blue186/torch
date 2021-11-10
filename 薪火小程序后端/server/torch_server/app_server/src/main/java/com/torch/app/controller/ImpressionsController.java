package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Article;
import com.torch.app.entity.Impressions;
import com.torch.app.service.ArticleService;
import com.torch.app.service.ImpressionsService;
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

@Api(tags = "{用户心得体会相关接口}",value = "用户心得体会相关接口")
@RestController
@RequestMapping("/impressions")
public class ImpressionsController {
    @Resource
    private ImpressionsService impressionsService;
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
    public R<?> publishImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody String content,
                               @ApiParam(name = "images",value = "图片流",required = true)@RequestBody MultipartFile[] images,
                                   @ApiParam(name = "actStars",value = "对志愿活动的评价等级")@RequestBody Integer actStars,
                                   @ApiParam(name = "actId",value = "志愿活动的id",required = true) Integer actId,

                                   HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            Impressions impressions = new Impressions();
            impressions.setContent(content);
            impressions.setActId(actId);
            impressions.setActStars(actStars);
            impressions.setUserId((Integer) uid);
            impressions.setCreateTime(new Date());
            impressions.setUpdateTime(new Date());
            String[] urls = fileUtil.uploadImage(images);
            String join = StringUtils.join(urls, ";");
            impressions.setActImages(join);

            int res = impressionsService.getBaseMapper().insert(impressions);
            if (res==1){
                return R.ok().message("发布成功");
            }else {
                return R.error().message("发布失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "用户删除已发布的文章接口")
    @DeleteMapping()
    public R<?> deleteImpressions(@ApiParam(name = "id",value = "心得的id")@RequestBody Integer id, HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            int res = impressionsService.getBaseMapper().deleteById(id);
            if (res==1){
                return R.ok().message("删除成功");
            }else {
                return R.error().message("删除失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "用户修改心得")
    @PutMapping()
    public R<?> updateImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody String content,
                                  @ApiParam(name = "images",value = "图片流",required = true)@RequestBody MultipartFile[] images,
                                  @ApiParam(name = "actStars",value = "对志愿活动的评价等级")@RequestBody Integer actStars,
                                  @ApiParam(name = "actId",value = "志愿活动的id",required = true) Integer actId,
                              @ApiParam(name = "id",value = "心得id",required = true) Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Impressions impressions = impressionsService.getBaseMapper().selectById(id);
            impressions.setUpdateTime(new Date());
            impressions.setActStars(actStars);
            impressions.setContent(content);
            impressions.setActId(actId);

            String[] urls = fileUtil.uploadImage(images);
            String join = StringUtils.join(urls, ";");
            impressions.setActImages(join);
            int res = impressionsService.getBaseMapper().updateById(impressions);
            if (res==1){
                return R.ok().message("更新成功");
            }else {
                return R.error().message("更新失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "获取所有心得")
    @GetMapping("/{current}/{limit}")
    public R<?> getAllImpressions(@ApiParam(name = "current", value = "当前已经获取的数量", required = true) @PathVariable long current,
                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            Page<Impressions> page = new Page<>(current,limit);
            QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("update_time");
            wrapper.eq("user_id",uid);
            impressionsService.page(page,wrapper);
            List<Impressions> records = page.getRecords();
            return R.ok().message("查询成功").data(records);
        }else {
            return R.error().code(-100);
        }
    }
    @ApiOperation(value = "获取单篇心得的内容")
    @GetMapping("/{id}")
    public R<?> getOneImpressions(@ApiParam(name = "id",value = "心得的id",required = true)@PathVariable Integer id,HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            Impressions impressions = impressionsService.getBaseMapper().selectById(id);
            return R.ok().data(impressions);
        }else {
            return R.error().code(-100);
        }
    }
}
