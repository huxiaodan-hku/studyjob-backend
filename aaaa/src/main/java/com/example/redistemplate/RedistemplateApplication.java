package com.example.redistemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RedistemplateApplication {


    public static void main(String[] args) {

        SpringApplication.run(RedistemplateApplication.class, args
        );
    }


}
