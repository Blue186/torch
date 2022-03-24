package com.torch.app.util.commonutils;

/**
 * 用于存放有关缓存的时间、名字等
 */
public interface CacheCode {

    long TOKEN_TIME = (long) (3600+Math.random()*10);//用户登录时产生的token的有效时间,s

//    long USER_TIME = (long) (1440+Math.random()*10);//用户信息缓存有效时间,s
//
//    long ACTIVITY_TIME = (long) (480+Math.random()*10);//父活动有效时间,m
//
//    long ACTIVITY_CHILD_TIME = (long) (480+Math.random()*10);//子活动有效时间,m
//
//    long ACTIVITY_TIMES_TIME = (long) (480+Math.random()*10);//活动时间段有效时间,m
//
//    long SIGN_UP_TIME = (long) (480+Math.random()*10);//报名信息有效时间,m
//
//    long IMP_IMAGES_TIME = (long) (1440+Math.random()*10);//心得图片有效时间,m
//
//    long IMPRESSIONS_TIME = (long) (1440+Math.random()*10);//心得有效时间,m

    String CACHE_USER = "user:";//用户信息缓存前缀

    String CACHE_SIGN_UP = "signUp:";//报名信息缓存前缀

    String CACHE_IMPRESSION = "impression:";//心得信息缓存前缀

    String CACHE_ACTIVITY_CHILD = "activityChild:";//子活动信息缓存前缀

    String CACHE_ACTIVITY = "activity:";//父活动信息缓存前缀

    String CACHE_ACTIVITY_TIMES = "activityTimes:";//活动时间缓存前缀

    String CACHE_IMP_IMAGES = "impImages:";//心得图片缓存前缀

    String CACHE_ACTIVITY_INFO = "activityInfo:";//封装的志愿活动信息缓存前缀
}
