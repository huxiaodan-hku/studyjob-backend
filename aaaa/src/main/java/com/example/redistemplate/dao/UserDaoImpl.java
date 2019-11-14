package com.example.redistemplate.dao;

import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private Jedis jedis;

    @Override
    public User findByUsername(String username) {
        Map<String, String> user = jedis.hgetAll("user:" + username);
        return User.builder().name(user.get("name")).username(user.get("username")).password(user.get("password"))
                .userId(Long.parseLong(user.get("id"))).build();
    }

    @Override
    public void createNewUser(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("password", user.getPassword());
        userMap.put("id", String.valueOf(jedis.incr("userCount")));
        userMap.put("name", user.getName());
        jedis.hset("user:" + user.getUsername(), userMap);
    }

    @Override
    public boolean existsByUsername(String username) {
        Map<String, String> user = jedis.hgetAll("user" + username);
        return Objects.nonNull(user) && user.size() != 0;

    }

}
