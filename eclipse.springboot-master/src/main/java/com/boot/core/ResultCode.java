package com.boot.core;

public enum ResultCode {
    //平台抛出异常
    REGISTER_SUCCESS(999, "注册成功"),
    SYSTEM_ERROR(1000, "系统错误，请稍后重试"),
    USER_EXIST(1001, "用户已存在！"),
    USER_UNEXIST(1002, "用户不存在！"),
    LOGIN_SUCCESS(1003,"登陆成功"),
    PASSWORD_ERROR(1004,"密码错误")
    ;



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
