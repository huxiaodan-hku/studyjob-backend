package com.example.redistemplate.dao;

import com.example.redistemplate.constant.JedisConstant;
import com.example.redistemplate.dao.spec.GroupDao;
import com.example.redistemplate.entities.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    private static final String GROUP_ID = "groupId";
    private static final String GROUP_NAME = "groupName";
    private static final String CREATE_TIME = "createTime";

    @Autowired
    private Jedis jedis;

    @Override
    public boolean createGroup(GroupDto groupDto){
        long groupId = groupDto.getGroupId();
        String groupHashKey = groupHashKey(groupId);
        //save group  hash
        jedis.hset(groupHashKey,GROUP_ID,String.valueOf(groupId));
        jedis.hset(groupHashKey,GROUP_NAME,groupDto.getGroupName());
        jedis.hset(groupHashKey,CREATE_TIME,groupDto.getCreateTime());
        List<String> members = groupDto.getMembers();
        for(int i = 0;i<members.size();i++){
            jedis.sadd(membersKey(groupId),members.get(i));
        }
        //save members
        return true;
    }

    @Override
    public long generateGroupId() {
        return jedis.incr(JedisConstant.GROUP_ID_COUNT);
    }

    private String groupHashKey(long groupId){
        return JedisConstant.GROUP_HASHKEY_PREFIX +groupId;
    }

    private String membersKey(long groupId){
        return JedisConstant.MEMBER_HASHKEY_PREFIX +groupId;
    }

}
