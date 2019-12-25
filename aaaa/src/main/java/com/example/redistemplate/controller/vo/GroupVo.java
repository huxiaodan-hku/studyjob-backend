package com.example.redistemplate.controller.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupVo {
    long id;
    List<String> members;
    String groupName;
    String createTime;
}
