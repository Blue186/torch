package com.torch.app.util.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.torch.app.entity.*;
import com.torch.app.entity.vo.ActivityChildCon.GetChild;
import com.torch.app.service.*;
import com.torch.app.util.commonutils.CacheCode;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行缓存添加任务
 */
@Component
public class RedissonCache {

    private RedissonClient redissonClient;
    private UserService userService;
//    private ThumbsService thumbsService;
    private SignUpService signUpService;
    private ImpressionsService impressionsService;
    private ContactUsService contactUsService;
//    private ArticleService articleService;
//    private ArtCommentService artCommentService;
    private ActivityChildService activityChildService;
    private ActivityService activityService;
    private ActivityTimesService activityTimesService;
    private ImpImagesService impImagesService;

    @Autowired
    public RedissonCache(RedissonClient redissonClient,
                         UserService userService,
                         SignUpService signUpService,
                         ImpressionsService impressionsService,
                         ContactUsService contactUsService,
                         ActivityChildService activityChildService,
                         ActivityService activityService,
                         ActivityTimesService activityTimesService,
                         ImpImagesService impImagesService) {
        this.redissonClient = redissonClient;
        this.userService = userService;
        this.signUpService = signUpService;
        this.impressionsService = impressionsService;
        this.contactUsService = contactUsService;
        this.activityChildService = activityChildService;
        this.activityService = activityService;
        this.activityTimesService = activityTimesService;
        this.impImagesService = impImagesService;
    }


    /**
     * 用户信息缓存定时更新
     */
    @Scheduled(cron = "0 0 8 1/1 1-12 ?")
    public void userCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> users = userService.getBaseMapper().selectList(wrapper);//拿到所有的users
        for (User user : users) {
            String key = CacheCode.CACHE_USER+user.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(user,CacheCode.USER_TIME,TimeUnit.MINUTES);//这里在添加到布隆过滤器中的同时，添加到redis缓存。
        }
    }

    /**
     * 报名信息定时更新
     */
    @Scheduled(cron = "0 0 6,14,22 1/1 1-12 ?")
    private void signUpCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<SignUp> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<SignUp> signUps = signUpService.getBaseMapper().selectList(wrapper);
        for (SignUp signUp : signUps) {
            String key = CacheCode.CACHE_SIGN_UP+signUp.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(signUp,CacheCode.SIGN_UP_TIME,TimeUnit.MINUTES);
        }
    }

    /**
     * 心得缓存定时更新
     */
    @Scheduled(cron = "0 0 12 1/1 1-12 ?")
    private void impressionsCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<Impressions> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Impressions> impressions = impressionsService.getBaseMapper().selectList(wrapper);
        for (Impressions impression : impressions) {
            String key = CacheCode.CACHE_IMPRESSION+impression.getActId()+impression.getUserId();//前端只能通过活动名和用户名来获取缓存，而不是impId
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(impression,CacheCode.IMPRESSIONS_TIME,TimeUnit.MINUTES);
        }
    }

    /**
     * 子活动缓存定时更新
     */
    @Scheduled(cron = "0 0 6,14,22 1/1 1-12 ?")
    private void activityChildCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<ActivityChild> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<ActivityChild> activityChildren = activityChildService.getBaseMapper().selectList(wrapper);
        for (ActivityChild activityChild : activityChildren) {
            String key = CacheCode.CACHE_ACTIVITY_CHILD+activityChild.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(activityChild,CacheCode.ACTIVITY_CHILD_TIME,TimeUnit.MINUTES);
        }
    }

    /**
     * 父活动信息定时更新
     */
    @Scheduled(cron = "0 0 6,14,22 1/1 1-12 ?")
    private void activityCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<Activity> activities = activityService.getBaseMapper().selectList(wrapper);
        for (Activity activity : activities) {
            String key = CacheCode.CACHE_ACTIVITY+activity.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(activity,CacheCode.ACTIVITY_TIME,TimeUnit.MINUTES);
        }
    }

    /**
     * 志愿活动时间的缓存
     */
    @Scheduled(cron = "0 0 6,14,22 1/1 1-12 ?")
    private void activityTimesCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<ActivityTimes> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<ActivityTimes> activityTimes = activityTimesService.getBaseMapper().selectList(wrapper);
        for (ActivityTimes activityTime : activityTimes) {
            String key = CacheCode.CACHE_ACTIVITY_TIMES+activityTime.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(activityTime,CacheCode.ACTIVITY_TIMES_TIME,TimeUnit.MINUTES);
        }
    }

    /**
     * 心得图片的缓存
     */
//    @Scheduled(cron = "0 0 12 1/1 1-12 ?")
    @Scheduled(cron = "0 21 17 1/1 1-12 ?")
    private void impImagesCacheUpdate(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<ImpImages> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<ImpImages> impImages = impImagesService.getBaseMapper().selectList(wrapper);
        for (ImpImages impImage : impImages) {
            String key = CacheCode.CACHE_IMP_IMAGES+impImage.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).trySet(impImage,CacheCode.IMP_IMAGES_TIME, TimeUnit.MINUTES);
        }
    }

    /**
     * 这里添加志愿活动详细信息到缓存中
     */
//    @Scheduled(cron = "0 0 6,14,22 1/1 1-12 ?")
    @Scheduled(cron = "0 21 17 1/1 1-12 ?")
    private void activityInfo(){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //拿到所有的父活动
        List<Activity> activities = activityService.getBaseMapper().selectList(wrapper);
        //遍历父活动并进行封装
        for (Activity activity : activities) {
            //父活动包含有许多的子活动，用列表进行添加
            List<GetChild> getChildren = new ArrayList<>();
            //拿到当前父活动对应的所有的子活动
            List<ActivityChild> activityChildren = activityChildService.selectChild(activity.getId());//这是子活动
            //这里对子活动的信息进行提取封装成列表
            for (ActivityChild activityChild : activityChildren) {
                getChildren.add(activityChildService.setGetChild(activityChild));
            }
            //将信息添加到布隆过滤器和redis缓存中
            String key = CacheCode.CACHE_ACTIVITY_INFO+activity.getId();
            bloomFilter.add(key);
            redissonClient.getBucket(key).set(getChildren);
        }
    }

}
