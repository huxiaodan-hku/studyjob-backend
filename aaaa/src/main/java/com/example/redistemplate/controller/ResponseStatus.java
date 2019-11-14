package com.example.redistemplate.controller;

public enum ResponseStatus {
    ERROR(0),SUCCESS(1);

    private int status;

    ResponseStatus(int status){
        this.status = status;
    }

    public int getResponseStatus(){
        return status;
    }

}

