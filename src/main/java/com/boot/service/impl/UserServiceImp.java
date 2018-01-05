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
        user.setUserId(10086L);
        user.setUserName(userVo.getUserName());
        user.setUserPhone("15967502105");
        userRepository.save(user);
    }
}
