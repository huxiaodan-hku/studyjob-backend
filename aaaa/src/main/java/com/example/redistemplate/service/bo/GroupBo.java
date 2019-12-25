package com.example.redistemplate.service.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupBo {
    private long groupId;
    private String groupName;
    private List<String> members;
    private String createTime;

}
