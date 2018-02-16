/**
 * 
 */
/**
 * @author 石乾
 * @Edit ChenXiang
 */
package com.boot.controller;

import com.boot.bean.base.HttpResult;
import com.boot.bean.vo.UserVo;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//配置RestController注解，表示这个是Rest风格的controller，用来处理json的数据
@RestController
@RequestMapping(value = "/user")
public class MainController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public HttpResult registUser(UserVo userVo) throws BusiException {
        userService.registeUser(userVo);
        HttpResult httpResult=new HttpResult();
        httpResult.setErrorCode(ResultCode.REGISTER_SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.REGISTER_SUCCESS.getMessage());
        return httpResult;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HttpResult loginUser(UserVo userVo) throws BusiException {
        userService.loginUser(userVo);
        HttpResult httpResult=new HttpResult();
        httpResult.setErrorCode(ResultCode.LOGIN_SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.LOGIN_SUCCESS.getMessage());
        return httpResult;
    }
}