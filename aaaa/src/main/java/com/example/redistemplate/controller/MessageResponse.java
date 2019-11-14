package com.example.redistemplate.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageResponse {
    private String responseMessage;
    private ResponseStatus responseStatus;
}
