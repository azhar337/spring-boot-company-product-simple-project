package com.example.demo.constant;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    ERR_FAIL_INSERT("FE_1234","Fail to insert"),
    ERR_FAIL_RETRIEVE("FE_12345","Fail to retrieve"),
    ERR_FAIL_UPDATE("FE_123456","Fail to update"),
    ERR_FAIL_DELETE("FE_1234567","Fail to delete")
    ;

    private final String code;
    private final String message;
    
    ErrorCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
}
