package com.example.redistemplate.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class WorkSpace {
    long id;
    String creator;
    List<String> userAccounts;
}
