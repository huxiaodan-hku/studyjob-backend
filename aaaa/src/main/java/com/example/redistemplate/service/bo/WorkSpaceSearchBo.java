package com.example.redistemplate.service.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkSpaceSearchBo {
    List<String> workspace;
    String cursor;
}
