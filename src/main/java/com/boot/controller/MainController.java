/**
 * 
 */
/**
 * @author 石乾
 * @Edit ChenXiang
 */
package com.boot.controller;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.User;
import com.boot.bean.vo.*;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//配置RestController注解，表示这个是Rest风格的controller，用来处理json的数据
@RestController
@RequestMapping(value = "/user")
public class MainController {
    @Autowired
    UserService userService;
    UserListVo httpResult = new UserListVo();
    UserRoleVo login_name = new UserRoleVo();

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public HttpResult registUser(UserVo userVo) throws BusiException {
        userService.registeUser(userVo);
        HttpResult httpResult = new HttpResult();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        return httpResult;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpResult loginUser(UserVo userVo) throws BusiException {
        userService.loginUser(userVo);
        login_name.setErrorCode(ResultCode.SUCCESS.getCode());
        login_name.setErrorMessage(ResultCode.SUCCESS.getMessage());
        login_name.setRole(userVo.getUserRole());//区分跳转页面
        login_name.setName(userVo.getUserName());//方便学生教师登陆界面的名字
        return login_name;
    }

    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public HttpResult getName() throws BusiException {
        List<User> usersList = userService.findUserByName(login_name.getName());//返回给userlist
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/database", method = RequestMethod.POST)
    public HttpResult queryUsers(UserQueryVo userQueryVo) throws BusiException {
        int size = userQueryVo.getSize();
        int count = userService.getCount();
        int total;
        if (count % size == 0) {
            total = count / size;
        } else {
            total = count / size + 1;
        }
        List<User> usersList = userService.findUserByPage(userQueryVo.getIndex(), userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        httpResult.setPage(1);
        httpResult.setTotalPage(total);  //整除
        return httpResult;
    }

    @RequestMapping(value = "/pageUp", method = RequestMethod.POST)
    public HttpResult queryLastPage(UserQueryVo userQueryVo) throws BusiException {
        httpResult.setPage(httpResult.getPage() - 1);
        userQueryVo.setIndex((httpResult.getPage() - 1) * userQueryVo.getSize());
        List<User> usersList = userService.findUserByPage(userQueryVo.getIndex(), userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/pageDown", method = RequestMethod.POST)
    public HttpResult queryNextPage(UserQueryVo userQueryVo) throws BusiException {
        httpResult.setPage(httpResult.getPage() + 1);
        userQueryVo.setIndex((httpResult.getPage() - 1) * userQueryVo.getSize());
        List<User> usersList = userService.findUserByPage(userQueryVo.getIndex(), userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HttpResult deleteUsers(@RequestBody UserNamesVo userNamesVo) throws BusiException {
        UserQueryVo userQueryVo = new UserQueryVo();
        userService.deleteUsers(userNamesVo);
        userQueryVo.setIndex((httpResult.getPage() - 1) * userQueryVo.getSize());
        List<User> usersList = userService.findUserByPage(userQueryVo.getIndex(), userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        int size = userQueryVo.getSize();
        int count = userService.getCount();
        int total;
        if (count % size == 0) {
            total = count / size;
        } else {
            total = count / size + 1;
        }
        httpResult.setTotalPage(total);
        return httpResult;
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    public HttpResult passUsers(@RequestBody UserNamesVo userNamesVo) throws BusiException {
        userService.passUsers(userNamesVo);
        List<User> usersList = userService.findUserByState();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public HttpResult searchUsers(UserVo userVo) throws BusiException {
        List<User> usersList = userService.searchUser(userVo.getUserName());
        UserListVo httpResult = new UserListVo();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public HttpResult queryUser(UserQueryVo userQueryVo) throws BusiException {
        List<User> usersList = userService.findUserByState();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        httpResult.setPage(1);
        httpResult.setTotalPage(1);
        return httpResult;
    }

    @RequestMapping(value = "/entrance", method = RequestMethod.POST)
    public HttpResult entrance() throws BusiException {
        User user=userService.findByName(login_name.getName());
        login_name.setErrorCode(ResultCode.SUCCESS.getCode());
        login_name.setErrorMessage(ResultCode.SUCCESS.getMessage());
        login_name.setEntrance(user.getUserEntrance());
        return login_name;
    }

    @RequestMapping(value = "/study", method = RequestMethod.POST)
    public HttpResult study() throws BusiException {
        User user=userService.findByName(login_name.getName());
        login_name.setErrorCode(ResultCode.SUCCESS.getCode());
        login_name.setErrorMessage(ResultCode.SUCCESS.getMessage());
        login_name.setStudy(user.getUserStudy());
        return login_name;
    }

    @RequestMapping(value = "/graduation", method = RequestMethod.POST)
    public HttpResult graduation() throws BusiException {
        User user=userService.findByName(login_name.getName());
        login_name.setErrorCode(ResultCode.SUCCESS.getCode());
        login_name.setErrorMessage(ResultCode.SUCCESS.getMessage());
        login_name.setGraduation(user.getUserGraduation());
        return login_name;
    }
}