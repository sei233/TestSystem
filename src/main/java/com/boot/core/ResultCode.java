package com.boot.core;

public enum ResultCode {
    //平台抛出异常
    SYSTEM_SUCCESS(999, "交易成功"),
    SYSTEM_ERROR(1000, "系统错误，请稍后重试"),
    USER_EXIST(1001, "用户已存在！");


    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultCode acquireResultCode(int code) {
        for (ResultCode rt : ResultCode.values()) {
            if (rt.getCode() == code) {
                return rt;
            }
        }
        return null;
    }

}
