package com.example.redistemplate.dao;


import com.example.redistemplate.contants.JedisConstants;
import com.example.redistemplate.entities.GroupDto;
import com.example.redistemplate.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;


@Service
public class GroupDaoImpl implements GroupDao {

    private static final String FIELD_GROUP_NAME = "groupName";
    private static final String FIELD_GROUP_ID = "groupId";
    private static final String FIELD_CREATE_TIME = "createTime";

    @Autowired
    private Jedis jedis;


    @Override public GroupDto saveGroup(List<String> users, String groupName) {
        long newGroupId = generateNewGroupId();
        String key = getGroupHashKey(newGroupId);
        jedis.hset(key, FIELD_GROUP_NAME, groupName);
        jedis.hset(key, FIELD_GROUP_ID, String.valueOf(newGroupId));
        String currentTime = TimeUtils.getCurrentTime();
        jedis.hset(key, FIELD_CREATE_TIME, currentTime);
        String membersHashKey = getMembersHashKey(newGroupId);
        users.stream().forEach(user -> jedis.sadd(membersHashKey, user));
        return GroupDto.builder().groupId(newGroupId).groupName(groupName).createTime(currentTime).members(users)
                .build();

    }

    private String getMembersHashKey(long newGroupId) {
        return JedisConstants.MEMBERS_HASHKEY_PREFIX + newGroupId;
    }

    private String getGroupHashKey(long groupId) {
        return JedisConstants.GROUP_HASHKEY_PREFIX + groupId;
    }

    private long generateNewGroupId() {
        return jedis.incr(JedisConstants.GROUP_ID);
    }
}
