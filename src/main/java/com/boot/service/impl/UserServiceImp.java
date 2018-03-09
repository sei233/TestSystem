package com.boot.service.impl;

import com.boot.bean.po.User;
import com.boot.bean.vo.UserNamesVo;
import com.boot.bean.vo.UserVo;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.dao.UserRepository;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Chenxiang on 2018/1/5.
 */
@Service
public class UserServiceImp implements UserService {
    //注入数据仓库
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public void registeUser(UserVo userVo) throws BusiException {
        User user=userRepository.findByUserName(userVo.getUserName());
        if(user!=null)
        {
            throw new BusiException(ResultCode.USER_EXIST);
        }
        if(userVo.getUserRole()>5){                                                //用的是userVo而不是user
            throw new BusiException(ResultCode.USER_ROLE_UNEXIST);
        }
        user=new User();
        user.setUserId(Random());
        user.setUserName(userVo.getUserName());
        user.setUserPassword(userVo.getUserPassword());
        user.setUserPhone(userVo.getUserPhone());
        user.setUserRole(userVo.getUserRole());
        if(userVo.getUserRole()!=1){
            user.setUserState(2);
        }else{
            user.setUserState(1);
        }
        userRepository.save(user);
    }

    public void loginUser(UserVo userVo) throws BusiException {
        User user=userRepository.findByUserName(userVo.getUserName());
        if(user==null)
        {
            throw new BusiException(ResultCode.USER_UNEXIST);
        }
        if(!user.getUserPassword().equals(userVo.getUserPassword())){
            throw new BusiException(ResultCode.PASSWORD_ERROR);
        }
        if(user.getUserRole()!=userVo.getUserRole()||userVo.getUserRole()>6){                      //用的是userVo而不是user
            throw new BusiException(ResultCode.USER_ROLE_ERROR);
        }
        if(user.getUserState()!=1){                      //用的是userVo而不是user
            throw new BusiException(ResultCode.USER_STATE_ERROR);
        }
    }

    //生成id
    public long Random(){
            long t = System.currentTimeMillis();//获得当前时间的毫秒数
            return t;
    }

    @Override
    public void deleteUsers(UserNamesVo userNames) {
        Iterator it = userNames.getUserNames().iterator();      //遍历userNames，然后一个一个删除
        while(it.hasNext()){
            String s = (String) it.next();
            User user=userRepository.findByUserName(s);
            userRepository.delete(user);
        }
    }

    @Override
    public void passUsers(UserNamesVo userNamesVo) {
        Iterator it = userNamesVo.getUserNames().iterator();      //遍历userNames，然后一个一个删除
        while(it.hasNext()){
            String s = (String) it.next();
            User user=userRepository.findByUserName(s);
            user.setUserState(1);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> findAllUser() throws BusiException {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByPage(int index, int size) throws BusiException {
        return userRepository.selectUser(index,size);
    }

    @Override
    public List<User> findUserByState() {
        return userRepository.findByUserState(2);        //更改UserRepository的数据类型
    }

    @Override
    public List<User> searchUser(String userName) {
        List<User> userList=new ArrayList();
        User user=userRepository.findByUserName(userName);
        userList.add(user);
        return userList;
    }

    //获取总记录数
    public int getCount() {
        return userRepository.getCount();
    }
}
