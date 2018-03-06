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
    UserListVo httpResult=new UserListVo();
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public HttpResult registUser(UserVo userVo) throws BusiException {
        userService.registeUser(userVo);
        HttpResult httpResult=new HttpResult();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        return httpResult;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HttpResult loginUser(UserVo userVo) throws BusiException {
        userService.loginUser(userVo);
        UserRoleVo httpResult=new UserRoleVo();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setRole(userVo.getUserRole());
        return httpResult;
    }

    @RequestMapping(value = "/database",method = RequestMethod.POST)
    public HttpResult queryUsers(UserQueryVo userQueryVo) throws BusiException {
        int size=userQueryVo.getSize();
        int count=userService.getCount();
        List<User> usersList=userService.findUserByPage(userQueryVo.getIndex(),userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        httpResult.setPage(1);
        httpResult.setTotalPage(count/size);  //整除
        return httpResult;
    }

    @RequestMapping(value = "/pageUp",method = RequestMethod.POST)
    public HttpResult queryLastPage(UserQueryVo userQueryVo) throws BusiException {
        httpResult.setPage(httpResult.getPage()-1);
        userQueryVo.setIndex((httpResult.getPage()-1)*userQueryVo.getSize());
        List<User> usersList=userService.findUserByPage(userQueryVo.getIndex(),userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/pageDown",method = RequestMethod.POST)
    public HttpResult queryNextPage(UserQueryVo userQueryVo) throws BusiException {
        httpResult.setPage(httpResult.getPage()+1);
        userQueryVo.setIndex((httpResult.getPage()-1)*userQueryVo.getSize());
        List<User> usersList=userService.findUserByPage(userQueryVo.getIndex(),userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public HttpResult deleteUsers(UserNamesVo userNamesvo) throws BusiException {
        userNamesvo.getUserNamesVo();
        UserQueryVo userQueryVo=new UserQueryVo();
        userService.deleteUsers(userNamesvo);
        userQueryVo.setIndex((httpResult.getPage()-1)*userQueryVo.getSize());
        List<User> usersList=userService.findUserByPage(userQueryVo.getIndex(),userQueryVo.getSize());
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }
}