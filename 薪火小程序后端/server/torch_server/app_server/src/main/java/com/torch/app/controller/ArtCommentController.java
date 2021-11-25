package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.ArtComment;
import com.torch.app.entity.vo.CommentCon.PublishComment;
import com.torch.app.service.ArtCommentService;
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

@Api(tags = {"文章评论发布相关接口"},value = "文章评论发布相关接口")
@RestController
@RequestMapping("/comments")
public class ArtCommentController {
    @Resource
    private ArtCommentService artCommentService;
    @Resource
    private JudgeCookieToken judgeCookieToken;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "用户填写评论信息接口")
    @PostMapping()
    public R<?> publishComment(@ApiParam(name = "publishCom",value = "评论发布信息",required = true) PublishComment publishCom,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            String cookie = judgeCookieToken.getCookie(request);
            Object uid = redisUtil.hmGet(cookie, "uid");

            ArtComment artComment = artCommentService.getBaseMapper().selectById((Integer) uid);
            artComment.setContent(publishCom.getContent());
            artComment.setArtId(publishCom.getArtId());
            artComment.setAuthorId((Integer) uid);
            artComment.setCreateTime(new Date());
            artComment.setUpdateTime(new Date());
            artComment.setThumbsUp(0);
            int res = artCommentService.getBaseMapper().insert(artComment);
            if (res==1){
                return R.ok().message("评论成功");
            }else {
                return R.error().message("评论失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

    @ApiOperation(value = "删除评论")
    @DeleteMapping("/{id}")
    public R<?> deleteComment(@ApiParam(name = "id",value = "评论id",required = true)@PathVariable Integer id,
                              HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            int res = artCommentService.getBaseMapper().deleteById(id);
            if (res==1){
                return R.ok().message("删除成功");
            }else {
                return R.error().message("删除失败");
            }
        }else {
            return R.error().code(-100);
        }
    }

//    不知道需不需要修改，可以直接删除重写就是，修改---不写了吧。
//    @ApiOperation(value = "修改评论")
//    public R<?> updateComment(){
//
//    }
    @ApiOperation(value = "获取文章的所有评论")
    @GetMapping("/{artId}")
    public R<?> selectComments(@ApiParam(name = "artId",value = "文章id",required = true)@PathVariable Integer artId,
                               HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (judge){
            QueryWrapper<ArtComment> wrapper = new QueryWrapper<>();
            wrapper.eq("art_id",artId);
            List<ArtComment> artComments = artCommentService.getBaseMapper().selectList(wrapper);
            return R.ok().data(artComments);
        }else {
            return R.error().code(-100);
        }
    }
}
