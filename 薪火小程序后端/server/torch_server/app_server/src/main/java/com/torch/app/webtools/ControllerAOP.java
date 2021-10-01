package com.torch.app.webtools;

import com.torch.app.webtools.annotation.LogCostTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ControllerAOP {
    final static Logger LOG = LoggerFactory.getLogger(LogCostTime.class);

    @Value("${log.cost.time.enable:true}")
    private boolean logCostTimeEnable;

    @Pointcut("@annotation(com.torch.server.webtools.annotation.LogCostTime)")
    public void costTimePointCut() {

    }

    @Around("costTimePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 判断开关
        if (!logCostTimeEnable) return point.proceed();
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Object proceed = point.proceed();
        long time = System.currentTimeMillis() - startTime;
        logCostTime(point, time);
        return proceed;
    }

    private void logCostTime(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        String name = point.getTarget().getClass().getName();
        // 类名 方法名 执行时间
        LOG.info("{}.{} cost:{}ms", name, signature.getName(), time);
    }

}
