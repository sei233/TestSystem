/**
 * 
 */
/**
 * @author 石乾
 * @Edit ChenXiang
 */
package com.boot.controller;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Entrance;
import com.boot.bean.po.Test;
import com.boot.bean.po.Test2;
import com.boot.bean.po.User;
import com.boot.bean.vo.*;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//配置RestController注解，表示这个是Rest风格的controller，用来处理json的数据
@RestController
@RequestMapping(value = "/user")
public class MainController {
    @Autowired
    UserService userService;
    UserListVo httpResult = new UserListVo();       //userList+page+totalpage
    UserRoleVo login_name = new UserRoleVo();       //id name entrance study graduation
    @Autowired
    TestService testService;
    TestListVo Result=new TestListVo();            //testList+page+totalpage
    @Autowired
    EntrService entrService;
    @Autowired
    Entr2Service entr2Service;
    @Autowired
    Test2Service test2Service;                        //要有接口+Impl+Repository
    Test2ListVo Result2=new Test2ListVo();            //testList+page+totalpage


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

    @RequestMapping(value = "/search2", method = RequestMethod.POST)
    public HttpResult search2Users(UserVo userVo) throws BusiException {
        List<User> usersList = userService.search2User(userVo.getUserName());
        UserListVo httpResult = new UserListVo();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/search1", method = RequestMethod.POST)
    public HttpResult search1Users(UserVo userVo) throws BusiException {
        List<User> usersList = userService.search1User(userVo.getUserName());
        UserListVo httpResult = new UserListVo();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/search3", method = RequestMethod.POST)
    public HttpResult search3Users(UserVo userVo) throws BusiException {
        List<User> usersList = userService.search3User(userVo.getUserName());
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
        if (login_name.getEntrance()==1){
            throw new BusiException(ResultCode.User_TEST_ERROR);
        }
        return login_name;
    }

    @RequestMapping(value = "/entrance_repo", method = RequestMethod.POST)
    public HttpResult entranceRepo() throws BusiException {
        List<Entrance> entrances=entrService.findEntrByStudent(login_name.getName());
        EntrListVo httpResult = new EntrListVo();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setEntrList(entrances);
        return httpResult;
    }

    @RequestMapping(value = "/ensure", method = RequestMethod.POST)
    public HttpResult ensureUser(@RequestBody UserNamesVo userNamesVo) throws BusiException {
        userService.ensureUsers(userNamesVo);
        List<User> usersList = userService.findUserByEntrance();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        return httpResult;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public HttpResult test1(TestQueryVo testQueryVo) throws BusiException {
        int size = testQueryVo.getSize();
        int count =testService.getCount();
        int total;
        if (count % size == 0) {
            total = count / size;
        } else {
            total = count / size + 1;
        }
        List<Test> testsList = testService.findTestByPage(testQueryVo.getIndex(), testQueryVo.getSize());
        Result.setErrorCode(ResultCode.SUCCESS.getCode());
        Result.setErrorMessage(ResultCode.SUCCESS.getMessage());
        Result.setTestList(testsList);
        Result.setPage(1);
        Result.setTotalPage(total);  //整除
        return Result;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public HttpResult test2(Test2QueryVo test2QueryVo) throws BusiException {
        List<Test2> testsList = test2Service.findTestByPage(test2QueryVo.getIndex(), test2QueryVo.getSize());
        Result2.setErrorCode(ResultCode.SUCCESS.getCode());
        Result2.setErrorMessage(ResultCode.SUCCESS.getMessage());
        Result2.setTest2List(testsList);
        Result2.setPage(1);
        Result2.setTotalPage(1);  //整除
        return Result2;
    }

    @RequestMapping(value = "/stu_ans1", method = RequestMethod.POST)
    public HttpResult storeTests1(@RequestBody TestStuVo testStuVo) throws BusiException {      //存储成功
        String name=login_name.getName();
        entrService.calculate(testStuVo,name);
      //  userService.setUserEntrance(name,1);
        HttpResult httpResult=new HttpResult();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        return httpResult;
    }

    @RequestMapping(value = "/stu_ans2", method = RequestMethod.POST)
    public HttpResult storeTests2(@RequestBody TestStuVo testStuVo,Test2QueryVo test2QueryVo) throws BusiException {
        String name=login_name.getName();
        List<Test2> QA=Result2.getTest2List();
        List<Integer> Answer=new ArrayList();
        for(int i=0;i<QA.size();i++){
            Test2 test=QA.get(i);
            Answer.add(test.getAnswer());
        }
        entr2Service.calculate(testStuVo,name,Answer);
        HttpResult httpResult=new HttpResult();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        return httpResult;
    }

    @RequestMapping(value = "/pageDown_test1", method = RequestMethod.POST)
    public HttpResult queryNextPage_test1(TestQueryVo testQueryVo) throws BusiException {
        Result.setPage(Result.getPage() + 1);
        testQueryVo.setIndex((Result.getPage() - 1) * testQueryVo.getSize());
        List<Test> testsList = testService.findTestByPage(testQueryVo.getIndex(), testQueryVo.getSize());
        Result.setErrorCode(ResultCode.SUCCESS.getCode());
        Result.setErrorMessage(ResultCode.SUCCESS.getMessage());
        Result.setTestList(testsList);
        return Result;
    }

    @RequestMapping(value = "/pageUp_test1", method = RequestMethod.POST)
    public HttpResult queryLastPage_test1(TestQueryVo testQueryVo) throws BusiException {
        Result.setPage(Result.getPage()-1);
        testQueryVo.setIndex((Result.getPage() - 1) * testQueryVo.getSize());
        List<Test> testsList = testService.findTestByPage(testQueryVo.getIndex(), testQueryVo.getSize());
        Result.setErrorCode(ResultCode.SUCCESS.getCode());
        Result.setErrorMessage(ResultCode.SUCCESS.getMessage());
        Result.setTestList(testsList);
        return Result;
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

    @RequestMapping(value = "/correct", method = RequestMethod.POST)
    public HttpResult queryStudent(TestQueryVo testQueryVo) throws BusiException {
        List<User> usersList = userService.findUserByEntrance();
        httpResult.setErrorCode(ResultCode.SUCCESS.getCode());
        httpResult.setErrorMessage(ResultCode.SUCCESS.getMessage());
        httpResult.setUsersList(usersList);
        httpResult.setPage(1);
        httpResult.setTotalPage(1);
        return httpResult;
    }
}