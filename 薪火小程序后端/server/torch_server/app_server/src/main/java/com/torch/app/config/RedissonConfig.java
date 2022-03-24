package com.torch.app.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private Integer database;


    @Bean
    public RedissonClient redissonClient(){
        RedissonClient redissonClient = null;
        Config config = new Config();
        String url = "redis://"+host+":"+port;
        config.useSingleServer().setAddress(url)
                .setPassword(password)
                .setDatabase(database);
        try{
            redissonClient = Redisson.create(config);
            RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("bloom-filter");
            bloomFilter.tryInit(500000,0.001);
            log.info("布隆过滤器初始化成功");
            return redissonClient;
        }catch (Exception e){
            log.info("布隆过滤器初始化失败");
            return null;
        }
    }
}
