package com.torch.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.Impressions;
import com.torch.app.entity.SignUp;
import com.torch.app.entity.vo.ImpressionsCon.ImpressionsInfo;
import com.torch.app.entity.vo.ImpressionsCon.PublishImpressions;
import com.torch.app.entity.vo.ImpressionsCon.UpdateImpressions;
import com.torch.app.service.ImpressionsService;
import com.torch.app.service.SignUpService;
import com.torch.app.util.commonutils.CacheCode;
import com.torch.app.util.commonutils.ResultCode;
import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Api(tags = {"用户心得体会相关接口"},value = "用户心得体会相关接口")
@RestController
@RequestMapping("/impressions")
public class ImpressionsController {

    private ImpressionsService impressionsService;

    private RedisUtil redisUtil;

    private JudgeCookieToken judgeCookieToken;

    private SignUpService signUpService;

    private RedissonClient redissonClient;

    @Autowired
    public ImpressionsController(ImpressionsService impressionsService,
                                 RedisUtil redisUtil,
                                 JudgeCookieToken judgeCookieToken,
                                 SignUpService signUpService,
                                 RedissonClient redissonClient) {
        this.impressionsService = impressionsService;
        this.redisUtil = redisUtil;
        this.judgeCookieToken = judgeCookieToken;
        this.signUpService = signUpService;
        this.redissonClient = redissonClient;
    }

    /**
     * 薪火推文在管理端接口，此处为用户所发文章相应接口
     */
    @LogCostTime
    @ApiOperation(value = "用户填写心得接口")
    @PostMapping()
    public R<?> publishImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody PublishImpressions publishImp,
                                   HttpServletRequest request){
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
            log.info("用户心得发布成功");
            redissonClient.getBucket(CacheCode.CACHE_IMPRESSION+impressions.getId()).set(impressions,CacheCode.IMPRESSIONS_TIME, TimeUnit.MINUTES);
            return R.ok().message("发布成功");
        }else {
            log.error("用户心得发布失败");
            return R.error().message("发布失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "用户删除已发布的心得接口")
    @DeleteMapping("/{id}")
    public R<?> deleteImpressions(@ApiParam(name = "id",value = "心得的id")@PathVariable Integer id){
        int res = impressionsService.getBaseMapper().deleteById(id);
        if (res==1){
            log.info("用户心得删除成功");
            redissonClient.getBucket(CacheCode.CACHE_IMPRESSION+id).delete();
            return R.ok().message("删除成功");
        }else {
            log.error("用户心得删除失败");
            return R.error().message("删除失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "用户修改心得")
    @PutMapping()
    public R<?> updateImpressions(@ApiParam(name = "impressions",value = "用户心得信息",required = true) @RequestBody UpdateImpressions updateImp){
        Impressions impressions = impressionsService.getBaseMapper().selectById(updateImp.getId());
        impressions.setUpdateTime(new Date().getTime());
        impressions.setActStars(updateImp.getActStars());
        impressions.setContent(updateImp.getContent());
        int res = impressionsService.getBaseMapper().updateById(impressions);
        impressionsService.updateImages(updateImp.getImagesUrls(), impressions.getId());//这里存在逻辑问题，如果第一次没有提交图片，这样就更新不了了，后面添加更多的图片也不行了。
        if (res==1){
            log.info("用户心得更新成功");
            redissonClient.getBucket(CacheCode.CACHE_IMPRESSION+impressions.getId()).set(impressions,CacheCode.IMPRESSIONS_TIME,TimeUnit.MINUTES);
            return R.ok().message("更新成功");
        }else {
            log.error("用户心得更新失败");
            return R.error().message("更新失败");
        }
    }

    @LogCostTime
    @ApiOperation(value = "获取单篇心得的内容")
    @GetMapping("/{actId}")
    public R<?> getOneImpressions(@ApiParam(name = "actId",value = "志愿活动的id",required = true)@PathVariable Integer actId,
                                  HttpServletRequest request){
        String cookie = judgeCookieToken.getCookie(request);
        Object uid = redisUtil.hmGet(cookie, "uid");
        QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        wrapper.eq("act_id",actId);
        //因为心得存储后只能通过父活动id＋用户id来获取，那么我们存储在redis中的缓存也应该是这种形式。
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        String key = CacheCode.CACHE_IMPRESSION+actId+uid;
        if (!bloomFilter.contains(key)){
            log.info("布隆过滤器中不存在该key");
            return R.error().setErrorCode(ResultCode.wrongMsg);
        }
        //布隆中存在
        Impressions impressions = (Impressions) redissonClient.getBucket(key).get();
        if (impressions==null){
            log.info("从数据库中拿到的心得信息并更新redis");
            impressions = impressionsService.getBaseMapper().selectOne(wrapper);
            redissonClient.getBucket(key).set(impressions,CacheCode.IMPRESSIONS_TIME,TimeUnit.MINUTES);
        }
        log.info("从redis中拿到的心得数据");
        ImpressionsInfo impressionsInfo = impressionsService.getImpressionsInfo(impressions);
        return R.ok().data(impressionsInfo);
    }
}
