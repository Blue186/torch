package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.torch.app.entity.Article;
import com.torch.app.entity.Impressions;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.vo.ImpressionsCon.ImpressionsInfo;
import com.torch.app.entity.vo.ImpressionsCon.PublishImpressions;
import com.torch.app.entity.vo.ImpressionsCon.UpdateImpressions;
import com.torch.app.service.ArticleService;
import com.torch.app.service.ImpressionsService;
import com.torch.app.service.SignUpService;
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

@Api(tags = {"用户心得体会相关接口"},value = "用户心得体会相关接口")
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
    private SignUpService signUpService;
    /**
     * 薪火推文在管理端接口，此处为用户所发文章相应接口
     */
    @ApiOperation(value = "用户填写心得接口")
    @PostMapping()
    public R<?> publishImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody PublishImpressions publishImp,
                                   HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        Impressions impressions = new Impressions();
        impressions.setContent(publishImp.getContent());
        impressions.setActId(publishImp.getActId());
        impressions.setActStars(publishImp.getActStars());
        impressions.setUserId((Integer) uid);
        impressions.setCreateTime(new Date().getTime());
        impressions.setUpdateTime(new Date().getTime());
        int res = impressionsService.getBaseMapper().insert(impressions);//这里存放一个心得
        impressionsService.insertImages(publishImp.getImagesUrls(), impressions.getId());//这里将传上来的Url存放在数据库中
        QueryWrapper<SignUp> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("act_id",publishImp.getActId());
        List<SignUp> signUps = signUpService.getBaseMapper().selectList(wrapper);
        for (SignUp signUp : signUps) {
            signUp.setImpWrote(1);
            signUpService.getBaseMapper().updateById(signUp);
        }//这里完成？？
        if (res==1){
            return R.ok().message("发布成功");
        }else {
            return R.error().message("发布失败");
        }
    }

    @ApiOperation(value = "用户删除已发布的心得接口")
    @DeleteMapping("/{id}")
    public R<?> deleteImpressions(@ApiParam(name = "id",value = "心得的id")@PathVariable Integer id,
                                  HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        int res = impressionsService.getBaseMapper().deleteById(id);
        if (res==1){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "用户修改心得")
    @PutMapping()
    public R<?> updateImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody UpdateImpressions updateImp,
                                  HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge) {
            return R.error().code(-100);
        }
        Impressions impressions = impressionsService.getBaseMapper().selectById(updateImp.getId());
        impressions.setUpdateTime(new Date().getTime());
        impressions.setActStars(updateImp.getActStars());
        impressions.setContent(updateImp.getContent());
        int res = impressionsService.getBaseMapper().updateById(impressions);
        impressionsService.updateImages(updateImp.getImagesUrls(), impressions.getId());
        if (res==1){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

//    @ApiOperation(value = "用户修改心得")
//    @PutMapping()
//    public R<?> updateImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody UpdateImpressions updateImp){
//
//        Impressions impressions = impressionsService.getBaseMapper().selectById(updateImp.getId());
//        impressions.setUpdateTime(new Date().getTime());
//        impressions.setActStars(updateImp.getActStars());
//        impressions.setContent(updateImp.getContent());
//        int res = impressionsService.getBaseMapper().updateById(impressions);
//        impressionsService.updateImages(updateImp.getImagesUrls(), impressions.getId());
//        if (res==1){
//            return R.ok().message("更新成功");
//        }else {
//            return R.error().message("更新失败");
//        }
//    }

//    @ApiOperation(value = "获取所有心得")
//    @GetMapping("/{current}/{limit}")
//    public R<?> getAllImpressions(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
//                               @ApiParam(name = "limit", value = "要获取的数量", required = true) @PathVariable long limit,
//                               HttpServletRequest request){
//        Boolean judge = judgeCookieToken.judge(request);
//        if (!judge) {
//            return R.error().code(-100);
//        }
//        String cookie = judgeCookieToken.getCookie(request);
//        Object uid = redisUtil.hmGet(cookie, "uid");
//        Page<Impressions> page = new Page<>(current,limit);
//        QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("update_time");
//        wrapper.eq("user_id",uid);
//        impressionsService.page(page,wrapper);
//        List<Impressions> records = page.getRecords();
//
//        List<ImpressionsInfo> impressionsInfos = impressionsService.getImpressionsInfo(records);
//
//        return R.ok().message("查询成功").data(impressionsInfos);
//    }

    @ApiOperation(value = "获取单篇心得的内容")
    @GetMapping("/{actId}")
    public R<?> getOneImpressions(@ApiParam(name = "actId",value = "志愿活动的id",required = true)@PathVariable Integer actId,
                                  HttpServletRequest request){
        Boolean judge = judgeCookieToken.judge(request);
        if (!judge){
            return R.error().code(-100);
        }
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("act_id",actId);
        Impressions impressions = impressionsService.getBaseMapper().selectOne(wrapper);
        ImpressionsInfo impressionsInfo = impressionsService.getImpressionsInfo(impressions);
        return R.ok().data(impressionsInfo);
    }
//    @ApiOperation(value = "获取单篇心得的内容")
//    @GetMapping("/{actId}")
//    public R<?> getOneImpressions(@ApiParam(name = "actId",value = "志愿活动的id",required = true)@PathVariable Integer actId,
//                                  Integer uid){
//
//        QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id",uid);
//        wrapper.eq("act_id",actId);
//        Impressions impressions = impressionsService.getBaseMapper().selectOne(wrapper);
//        ImpressionsInfo impressionsInfo = impressionsService.getImpressionsInfo(impressions);
//        return R.ok().data(impressionsInfo);
//    }
}
