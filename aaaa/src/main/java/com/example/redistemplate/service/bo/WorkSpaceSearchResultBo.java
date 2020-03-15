package com.example.redistemplate.service.bo;

import com.example.redistemplate.entities.WorkSpace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceSearchResultBo {
    List<WorkSpace> workSpaceList;
    String cursor;
}
