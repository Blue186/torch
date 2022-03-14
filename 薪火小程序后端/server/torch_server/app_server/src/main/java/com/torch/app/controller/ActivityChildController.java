package com.torch.app.controller;

import com.torch.app.entity.ActivityChild;
import com.torch.app.entity.vo.ActivityChildCon.GetChild;
import com.torch.app.service.ActivityChildService;
import com.torch.app.util.commonutils.CacheCode;
import com.torch.app.util.commonutils.ResultCode;
import com.torch.app.util.commonutils.R;
import com.torch.app.webtools.annotation.LogCostTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(tags = {"获取子活动相关接口"}, value = "获取子活动相关接口")
@RestController
@RequestMapping("/activityChild")
public class ActivityChildController {

    private ActivityChildService activityChildService;

    private RedissonClient redissonClient;


    //    构造器注入
    @Autowired
    public ActivityChildController(ActivityChildService activityChildService,
                                   RedissonClient redissonClient) {
        this.activityChildService = activityChildService;
        this.redissonClient = redissonClient;
    }
    /**
     * 获取志愿详情的子志愿活动
     * @param activityId 父活动id
     * @return 所有的子志愿活动
     */
    @LogCostTime
    @ApiOperation(value = "志愿详情页志愿信息")
    @GetMapping("/{activityId}")
    public R<?> getChild(@ApiParam(name = "activityId", value = "父活动id",required = true) @PathVariable Integer activityId){
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
        String key = CacheCode.CACHE_ACTIVITY_INFO+activityId;//存在缓存中的key,父活动的key
        if (!bloomFilter.contains(key)){
            log.info("布隆过滤器中没有该活动信息：{}",key);
            return R.error().setErrorCode(ResultCode.wrongMsg);
        }
        //接下来到缓存中去拿数据
        Object ActivityInfo = redissonClient.getBucket(key).get();
        if (ActivityInfo==null){//若缓存中没有，就查询数据库并更新缓存。
            //这里从数据库中拿数据 进行返回
            List<GetChild> getChildren = new ArrayList<>();
            List<ActivityChild> activityChildren = activityChildService.selectChild(activityId);//这是子活动
            for (ActivityChild activityChild : activityChildren) {
                getChildren.add(activityChildService.setGetChild(activityChild));
            }
            //现在进行缓存更新
            bloomFilter.add(key);
            redissonClient.getBucket(key).set(getChildren);
            //返回数据
            log.info("从数据库中拿到的志愿活动信息");
            return R.ok().data(getChildren);
        }
        log.info("从缓存中拿到的志愿活动信息");
        //这里，缓存中存在就直接返回数据即可
        return R.ok().data(ActivityInfo);
    }
}
