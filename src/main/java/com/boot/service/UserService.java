package com.boot.service;

import com.boot.bean.po.User;
import com.boot.bean.vo.UserVo;
import com.boot.core.BusiException;

/**
 * Created by Chenxiang on 2018/1/5.
 */
public interface UserService {
    void registeUser(UserVo user) throws BusiException;
}
