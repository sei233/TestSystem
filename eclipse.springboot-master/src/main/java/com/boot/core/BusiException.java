package com.boot.core;

import lombok.Data;

/**
 * Created by Chenxiang on 2018/1/5.
 */
@Data
public class BusiException extends Exception {
    private ResultCode errCode;

    private Integer errorCode;
    private String errorMessage;

    public BusiException(ResultCode errCode){
        this.errCode = errCode;
    }

    public BusiException( String  errorMessage){
        this.errorMessage = errorMessage;
    }

    public BusiException(Integer errorCode, String  errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResultCode getErrCode() {
        return errCode;
    }

}
