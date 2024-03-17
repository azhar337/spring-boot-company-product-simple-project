package com.example.demo.constant;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    SUCCESS("SUCCESS"),
    FAILED("SUCCESS")
    ;

    private String status;

    ResponseStatusEnum(String status){
        this.status = status;
    }
    
}
