package com.example.demo.util.common;

import java.io.Serializable;

import com.example.demo.constant.ResponseStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
    private ResponseStatusEnum responseStatusEnum;
    private T data;
    private String errorCode;
    private String errorDescription;

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(ResponseStatusEnum.SUCCESS, data, null, null);
    }

    public static <T> BaseResponse<T> fail(String errorCode, String errorDescription){
        return new BaseResponse<>(ResponseStatusEnum.FAILED, null, errorCode, errorDescription);
    }
}
