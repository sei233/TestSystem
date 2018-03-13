package com.boot.service.impl;

import com.boot.bean.po.Test;
import com.boot.core.BusiException;
import com.boot.dao.TestRepository;
import com.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class TestServiceImp implements TestService {
    //注入数据仓库
    @Autowired
    TestRepository testRepository;
    @Override
    @Transactional

    public List<Test> findAllTest() {
        return testRepository.findAll();
    }

    @Override
    public List<Test> findTestByPage(int index, int size) {
        return testRepository.selectTest(index,size);
    }
}