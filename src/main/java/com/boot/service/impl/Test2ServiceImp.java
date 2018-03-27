package com.boot.service.impl;

import com.boot.bean.po.Test;
import com.boot.bean.po.Test2;
import com.boot.dao.Test2Repository;
import com.boot.dao.TestRepository;
import com.boot.service.Test2Service;
import com.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class Test2ServiceImp implements Test2Service {
    //注入数据仓库
    @Autowired
    Test2Repository test2Repository;
    @Override
    @Transactional

    public List<Test2> findAllTest() {
        return test2Repository.findAll();
    }

    @Override
    public List<Test2> findTestByPage(int index, int size) {
        return test2Repository.selectTest(index,size);
    }

    @Override
    public int getCount() {
            return test2Repository.getCount();
    }
}