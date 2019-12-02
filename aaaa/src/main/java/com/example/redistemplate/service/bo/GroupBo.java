package com.example.redistemplate.service.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class GroupBo {
    private long groupId;
    private List<String> members;
    private String groupName;
    private String createTime;
}
