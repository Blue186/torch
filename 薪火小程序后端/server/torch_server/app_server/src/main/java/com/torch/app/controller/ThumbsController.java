package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.Article;
import com.torch.app.entity.Thumbs;
import com.torch.app.entity.User;
import com.torch.app.service.ArticleService;
import com.torch.app.service.ThumbsService;
import com.torch.app.service.UserService;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = {"文章点赞相关接口"},value = "文章点赞相关接口")
@RestController
@RequestMapping("/thumbs")
public class ThumbsController {

    @Resource
    private ThumbsService thumbsService;
    @Resource
    private JudgeCookieToken judgeCookieToken;
    @Resource
    private ArticleService articleService;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户点赞该文章")
    @PutMapping("/{id}")
    public R<?> thumbsUp(@ApiParam(name = "id",value = "文章id",required = true)@PathVariable Integer id,
                         HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            User user = userService.getBaseMapper().selectById((Integer) uid);

            Thumbs thumbs = new Thumbs();
            thumbs.setUserId(user.getId());
            thumbs.setUserName(user.getNickName());
            thumbs.setArtId(id);
            int res = thumbsService.getBaseMapper().insert(thumbs);
            if (res==1){
                Article article = articleService.getBaseMapper().selectById(id);
                article.setThumbsUp(article.getThumbsUp()+1);
                return R.ok().message("点赞成功");
            }else {
                return R.error().message("失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "取消点赞")
    @DeleteMapping("/{id}")
    public R<?> deleteThumbs(@ApiParam(name = "id",value = "文章id",required = true)@PathVariable Integer id,
                             HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");
            QueryWrapper<Thumbs> wrapper = new QueryWrapper<>();
            wrapper.eq("userId",uid);
            wrapper.eq("artId",id);
            int res = thumbsService.getBaseMapper().delete(wrapper);
            if (res==1){
                return R.ok().message("取消点赞");
            }else {
                return R.error().message("失败");
            }
        }else {
            return R.error().code(-100);
        }
    }


    @ApiOperation(value = "拿到所有点赞信息")
    @GetMapping("/{id}")
    public R<?> getThumbsInfo(@ApiParam(name = "id",value = "文章id",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            QueryWrapper<Thumbs> wrapper = new QueryWrapper<>();
            wrapper.eq("art_id",id);
            Integer thumbsNum = thumbsService.getBaseMapper().selectCount(wrapper);

            List<Thumbs> thumbs = thumbsService.getBaseMapper().selectList(wrapper);
            String[] names = new String[thumbs.size()];
            for (int i = 0; i < thumbs.size(); i++) {
                names[i] = thumbs.get(i).getUserName();
            }

            Map<String,Object> map = new HashMap<>();
            map.put("nums",thumbsNum);
            map.put("names",names);
            return R.ok().data(map);
        }else {
            return R.error().code(-100);
        }
    }
}
