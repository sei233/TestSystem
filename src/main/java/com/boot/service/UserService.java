package com.boot.service;

import com.boot.bean.po.User;
import com.boot.bean.vo.UserNamesVo;
import com.boot.bean.vo.UserVo;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Chenxiang on 2018/1/5.
 */
public interface UserService {
    void registeUser(UserVo user) throws BusiException;
    void loginUser(UserVo user) throws BusiException;
    void deleteUsers(UserNamesVo userNames) throws BusiException;
    void passUsers(UserNamesVo userNamesVo) throws BusiException;
    List<User> findAllUser() throws BusiException;
    List<User> findUserByPage(int index,int size) throws BusiException;
    int getCount()throws BusiException;
    List<User> search2User(String userName) throws BusiException;
    List<User> search1User(String userName) throws BusiException;
    List<User> findUserByState() throws BusiException;
    List<User> findUserByName(String name) throws BusiException;
    List<User> findUserByEntrance() throws BusiException;
    User findByName(String name) throws BusiException;
    void setUserEntrance(String name,int state)throws BusiException;
}

