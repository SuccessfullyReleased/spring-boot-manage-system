package com.demo.rbac.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
//@EnableCaching
public class RedisConfig {

//    @Bean
//    public RedisTemplate<String, String> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }

}
