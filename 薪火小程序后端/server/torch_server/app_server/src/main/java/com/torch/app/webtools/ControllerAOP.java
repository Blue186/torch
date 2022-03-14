package com.torch.app.webtools;

import com.torch.app.util.tools.JudgeCookieToken;
import com.torch.app.util.tools.RedisUtil;
import com.torch.app.webtools.annotation.LogCostTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Enumeration;

@Configuration
@Aspect
public class ControllerAOP {
    private Long startTime;
    private Long endTime;
    final static Logger LOG = LoggerFactory.getLogger(LogCostTime.class);

    private JudgeCookieToken judgeCookieToken;

    private RedisUtil redisUtil;

    @Autowired
    public ControllerAOP(JudgeCookieToken judgeCookieToken,RedisUtil redisUtil) {
        this.judgeCookieToken = judgeCookieToken;
        this.redisUtil = redisUtil;
    }


    @Pointcut("@annotation(com.torch.app.webtools.annotation.LogCostTime)")
    public void costTimePointCut() {

    }
    @Before("costTimePointCut()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String cookie = judgeCookieToken.getCookie(request);
//        Object openid = redisUtil.hmGet(cookie, "openid");
/**
 *          这个时候应该怎么判断这次请求具体的要求，据体的想法？可以拿到方法、参数、URL但是我怎么知道
 *他要干什么，有的也只是request，缓存主要应对查的部分吧，更新，新增后应该更新缓存的，
 *那么首先判断类型对吧，如果是Get，那我们就去缓存中拿数据，如果缓存中没有，就再去数据库中查，然后更新到缓存中
 *如果是Post，那么直接添加到数据库，然后更新缓存到redis
 * 如果是Put，那么直接更新数据库，然后更新缓存
 * 如果是Delete，那么直接删除缓存，再删除数据库。
 * 但是依然存在问题，拿到的参数并不一定是直接用来做查询反馈的，所以还是单独做吧，已经封装好的redisTemplate是可以完成这些操作的，
 * 包括判断key是否存在，新增key，删除key等，已经能满足需要了，暂时没办法实现封装。
 * 那就单独写！！
 */
        startTime = System.currentTimeMillis();
        LOG.info("请求时间：{}",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        LOG.info("请求IP：{}",request.getRemoteAddr());
//        LOG.info("请求用户：{}",openid.toString());
        Signature signature = joinPoint.getSignature();
        LOG.info("请求方式：{}，方法：{}",request.getMethod(),signature.getName());
        LOG.info("请求参数：{}",Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "costTimePointCut()")
    public void afterReturning() throws Throwable{
        endTime = System.currentTimeMillis();
        LOG.info("请求结束时间：{}",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        LOG.info("请求耗时：{}",(endTime-startTime));
    }

    /**
     *    异常抛出日志记录
     */
    @AfterThrowing(value = "costTimePointCut()",throwing = "throwable")
    public void afterThrowing(Throwable throwable){
        LOG.error("发生异常时间：{}",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        LOG.error("异常抛出：{}",throwable.getMessage());
    }

}
