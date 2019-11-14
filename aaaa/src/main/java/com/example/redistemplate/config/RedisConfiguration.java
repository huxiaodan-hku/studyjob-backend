package com.example.redistemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfiguration {
    @Bean
    public Jedis getJedis(){
        Jedis jedis = new Jedis("localhost");
        return jedis;
    }

}
