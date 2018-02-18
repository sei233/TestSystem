package com.boot.bean.base;

import lombok.Data;


/**
 * Create By chenxiang
 * 返回结果的基类
 */

@Data
public class HttpResult{
    private Integer errorCode;
    private String errorMessage;
    private int role;
}
