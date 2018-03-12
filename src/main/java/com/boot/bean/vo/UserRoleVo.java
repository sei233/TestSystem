package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import lombok.Data;

@Data
public class UserRoleVo extends HttpResult {
    private int role;
    private String Name;
    private int entrance;
    private int study;
    private int graduation;
}
