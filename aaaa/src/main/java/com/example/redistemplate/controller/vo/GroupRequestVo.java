package com.example.redistemplate.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequestVo {
    private List<String> members;
    private String groupName;
    //当前登录的用户。
//    private String userId;
}
