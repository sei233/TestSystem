package com.boot.service.impl;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Test;
import com.boot.bean.vo.TestStuVo;
import com.boot.core.BusiException;
import com.boot.dao.EntrRepository;
import com.boot.dao.TestRepository;
import com.boot.service.EntrService;
import com.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class EntrServiceImp implements EntrService {
    //注入数据仓库
    @Autowired
    EntrRepository entrRepository;
    @Override
    @Transactional
    public void calculate(TestStuVo testStuVo,String name) throws BusiException {
        Entrance entrance=new Entrance();
        entrance.setStudent(name);
        entrance.setConformity(1);
        entrance.setDominance(1);
        entrance.setExtroversion(1);
        entrance.setPatience(1);
        entrance.setSigma(1);
        entrRepository.save(entrance);
    }

}