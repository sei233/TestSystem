package com.boot.service.impl;

import com.boot.bean.po.User;
import com.boot.bean.vo.UserVo;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.dao.UserRepository;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        user=new User();
        user.setUserId(Random());
        user.setUserName(userVo.getUserName());
        user.setUserPassword(userVo.getUserPassword());
        user.setUserPhone(userVo.getUserPhone());
        user.setUserRole(userVo.getUserRole());
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
    }

    //生成id
    public long Random(){
            long t = System.currentTimeMillis();//获得当前时间的毫秒数
            Random rd = new Random(t);//作为种子数传入到Random的构造器中
            int id=rd.nextInt();//生成随即整数
            return id;
    }
}
