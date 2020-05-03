package com.haizhi.webdemo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = -8348144035683384615L;
    private boolean success;
    private T result;
    private String statusCode;
    private String message;

    public CommonResponse(T result) {
        this.success = true;
        this.result = result;
    }

    public CommonResponse(boolean flag, T result) {
        if (flag) {
            this.success = true;
            this.result = result;
        } else {
            this.success = false;
            this.statusCode = (String) result;
        }
    }

    public CommonResponse(String statusCode) {
        this.success = false;
        this.statusCode = statusCode;
    }

    public CommonResponse(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static CommonResponse success(Object result){
        return new CommonResponse(result);
    }

    public static CommonResponse error(String message){
        return new CommonResponse(false,message);
    }


}
