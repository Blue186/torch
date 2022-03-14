package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.ArtComment;
import com.torch.app.entity.vo.CommentCon.PublishComment;
import com.torch.app.service.ArtCommentService;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Semaphore;

@Slf4j
@Api(tags = {"文章评论发布相关接口"},value = "文章评论发布相关接口")
@RestController
@RequestMapping("/comments")
public class ArtCommentController {

    private ArtCommentService artCommentService;

    private JudgeCookieToken judgeCookieToken;

    private RedisUtil redisUtil;

    @Autowired
    public ArtCommentController(ArtCommentService artCommentService,
                                JudgeCookieToken judgeCookieToken,
                                RedisUtil redisUtil) {
        this.artCommentService = artCommentService;
        this.judgeCookieToken = judgeCookieToken;
        this.redisUtil = redisUtil;
    }
    @LogCostTime
    @ApiOperation(value = "用户填写评论信息接口")
    @PostMapping()
    public R<?> publishComment(@ApiParam(name = "publishCom",value = "评论发布信息",required = true) PublishComment publishCom,
                               HttpServletRequest request){
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            log.info("评论-线程正被占用中");
            return R.error().message("线程占用中");
        }
        int res = 0;
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        ArtComment artComment = artCommentService.setArtCommit((Integer) uid,publishCom);
        try {
            semaphore.acquire(1);
            res = artCommentService.getBaseMapper().insert(artComment);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(1);
        }
        if (res==1){
            return R.ok().message("评论成功");
        }else {
            return R.error().message("评论失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "删除评论")
    @DeleteMapping("/{id}")
    public R<?> deleteComment(@ApiParam(name = "id",value = "评论id",required = true)@PathVariable Integer id){
        Semaphore semaphore = new Semaphore(1);
        if (semaphore.availablePermits()==0){
            return R.error().message("线程占用中");
        }
        int res = 0;
        try {
            semaphore.acquire(1);
            res = artCommentService.getBaseMapper().deleteById(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(1);
        }
        if (res==1){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "获取文章的所有评论")
    @GetMapping("/{artId}")
    public R<?> selectComments(@ApiParam(name = "artId",value = "文章id",required = true)@PathVariable Integer artId){
        QueryWrapper<ArtComment> wrapper = new QueryWrapper<>();
        wrapper.eq("art_id",artId);
        wrapper.orderByDesc("create_time");
        List<ArtComment> artComments = artCommentService.getBaseMapper().selectList(wrapper);
        return R.ok().data(artComments);
    }
}
