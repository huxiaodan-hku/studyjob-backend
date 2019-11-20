package com.example.redistemplate.dao;

import com.example.redistemplate.entities.Role;
import com.example.redistemplate.entities.RoleName;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private Jedis jedis;

    @Override
    public User findByUsername(String username) {
        Map<String, String> user = jedis.hgetAll("user:" + username);
        if(Objects.isNull(user) || user.size() == 0){
            return null;
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().roleName(RoleName.ROLE_ADMIN).build());
        return User.builder().roles((roles)).lastName(user.get("lastName")).firstName(user.get("firstName"))
                .username(user.get("username"))
                .password(user.get("password"))
                .userId(Long.parseLong(user.get("id"))).build();
    }

    @Override
    public void createNewUser(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("password", user.getPassword());
        userMap.put("id", String.valueOf(jedis.incr("userCount")));
        userMap.put("lastName", user.getLastName());
        userMap.put("firstName", user.getFirstName());
        jedis.hset("user:" + user.getUsername(), userMap);
    }

    @Override
    public boolean existsByUsername(String username) {
        Map<String, String> user = jedis.hgetAll("user:" + username);
        return Objects.nonNull(user) && user.size() != 0;

    }

}
