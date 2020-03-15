package com.example.redistemplate.dao;

import com.example.redistemplate.dao.spec.WorkSpaceDao;
import com.example.redistemplate.entities.WorkSpace;
import com.example.redistemplate.service.bo.WorkSpaceSearchBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WorkSpaceDaoImpl implements WorkSpaceDao {

    public static final String WORKSPACE = "workspace:";
    public static final String LIST = "List";
    public static final String MEMBERS = "members";
    @Autowired
    private Jedis jedis;

    @Override
    public List<Long> getWorkSpaceList(String userAccount) {
        return jedis.smembers(generateWorkSpaceListHashKey(userAccount)).stream().map(
                id -> Long.parseLong(id)).collect(Collectors.toList());
    }

    @Override
    public WorkSpaceSearchBo searchAllWorkspaceIds(String token, String cursor, int limit){
        ScanParams scanParams = new ScanParams();
        scanParams.match(WORKSPACE+"*");
        scanParams.count(limit);
        ScanResult<String> scan = jedis.scan(cursor, scanParams);
        return WorkSpaceSearchBo.builder().workspace(scan.getResult()).cursor(scan.getCursor()).build();
    }

    @Override
    public List<WorkSpace> getWorkSpace(List<Long> workspaceIds) {
        return workspaceIds.stream().map(id->{
            Map<String, String> map = jedis.hgetAll(generateWorkSpaceHashKey(id));
            List<String> members = getWorkSpaceMembers(id);
            return WorkSpace.builder().id(id).creator(map.get("creator")).userAccounts(members).build();
        }).collect(Collectors.toList());
    }

    @Override
    public void saveWorkSpace(WorkSpace workSpace){
        jedis.hset(generateWorkSpaceHashKey(workSpace.getId()), "id", String.valueOf(workSpace.getId()));
        jedis.hset(generateWorkSpaceHashKey(workSpace.getId()), "creator", workSpace.getCreator());
    }
    @Override
    public void addWorkSpaceMember(long id, String userAccount){
        jedis.sadd(generateWorkSpaceMembersHashKey(id), userAccount);
    }

    @Override
    public void addWorkSpaceMembers(long id, List<String> userAccount){
        userAccount.stream().forEach(account->jedis.sadd(generateWorkSpaceMembersHashKey(id), account));
    }

    @Override
    public List<String> getWorkSpaceMembers(long workspaceId){
        return new ArrayList<>(jedis.smembers(generateWorkSpaceMembersHashKey(workspaceId)));
    }

    @Override
    public long generateNewWorkSpaceId(){
        return jedis.incr(WORKSPACE);
    }

    private String generateWorkSpaceListHashKey(String userAccount) {
        return WORKSPACE + LIST + userAccount;
    }

    private String generateWorkSpaceHashKey(long workSpaceId) {
        return WORKSPACE + workSpaceId;
    }

    private String generateWorkSpaceMembersHashKey(long workSpaceId) {
        return WORKSPACE + MEMBERS + workSpaceId;
    }

    private void test(){
        List<Integer> list = new ArrayList<>();
    }
    @Override
    public long getIdFromHashKey(String hashKey){
        return Long.parseLong(hashKey.replace(WORKSPACE, ""));
    }
}



