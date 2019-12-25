package com.example.redistemplate.controller;

import com.example.redistemplate.controller.vo.*;
import com.example.redistemplate.dao.spec.UserDao;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Jedis jedis;

    private static final String GROUP_NAME = "name";

    @PostMapping("/loadGroups")
    @ResponseBody
    public List<GroupVo> loadGroup(@RequestBody LoadGroupsRequestVo vo){
        String hashKey1 = "groupId:1";
        String hashKey2 = "groupId:2";
        String hashKey3 = "groupId:3";
        String groupName1 = "计科一班";
        String groupName2 = "计科二班";
        String groupName3 = "计科一班";
        jedis.hset(hashKey1, GROUP_NAME, groupName1);
        jedis.hset(hashKey2, GROUP_NAME, groupName2);
        jedis.hset(hashKey3, GROUP_NAME, groupName3);
        jedis.sadd("owner:1","1");
        jedis.sadd("owner:1","2");
        jedis.sadd("owner:1","3");


        String userId = vo.getUserId();
        String ownerKey = getOwnerKey(userId);
        List<String> groupIds = new ArrayList(jedis.smembers(ownerKey));
        List<GroupVo> groupVoList = new ArrayList<>();
        for(int i = 0;i<groupIds.size();i++){
            GroupVo groupVo = getGroupVoByGroupId(Long.parseLong(groupIds.get(i)));
            groupVoList.add(groupVo);
        }
        return groupVoList;
    }

    private String getOwnerKey(String userId){
        return "owner:"+userId;
    }
    private String getGroupKey(long groupId){
        return "groupId:"+groupId;
    }

    private GroupVo getGroupVoByGroupId(long groupId){
        String hashKey = getGroupKey(groupId);
        Map<String, String> resultMap = jedis.hgetAll(hashKey);
        String createTime = resultMap.get("createTime");
        String name = resultMap.get("name");
        List<String> members = getMembersByGroupId(groupId);
        return GroupVo.builder().members(members).groupName(name).id(groupId).createTime(createTime).build();
    }

    public List<String> getMembersByGroupId(long groupId){
        //我利用這個Id拼接出set members id
        //利用smemebers拿到數據
        String groupMembersKey = "members:"+groupId;
        List<String> members = (ArrayList<String>)(jedis.smembers(groupMembersKey));
        return members;
    }

    @ResponseBody
    @PostMapping("/userInfo")
    public UserInfoVo userInfo(@RequestBody UserInfoRequestVo userInfoRequest) {
        User user = userDao.findByUsername(userInfoRequest.getUsername());
        if(Objects.isNull(user)){
            return null;
        }
        return UserInfoVo.builder().userName(user.getLastName()).build();
    }

}
