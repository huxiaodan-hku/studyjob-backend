package com.example.redistemplate.controller.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupVo {
    //hashkey
    private long id;

    //群成员
    private List<String> members;

    //群创建时间
    private String createTime;
    private String groupName;

    //谁创建了这个group（群主）
    private String owner;
}
