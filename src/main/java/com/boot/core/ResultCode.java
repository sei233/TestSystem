package com.boot.core;

public enum ResultCode {
    //平台抛出异常
    SUCCESS(999, "成功"),
    SYSTEM_ERROR(1000, "系统错误，请稍后重试"),
    USER_EXIST(1001, "用户已存在！"),
    USER_UNEXIST(1002, "用户不存在！"),
    PASSWORD_ERROR(1004,"密码错误"),
    USER_ROLE_UNEXIST(1005,"用户角色不存在"),
    USER_ROLE_ERROR(1006,"用户角色错误"),
    USER_STATE_ERROR(1007,"等待管理员审核"),
    QUESTION_UNEXIST(1008,"题目未填满"),
    User_TEST_ERROR(1009,"等待教师审核")
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
