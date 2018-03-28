package com.boot.service.impl;

import com.boot.bean.po.Test2;
import com.boot.bean.po.Test3;
import com.boot.dao.Test2Repository;
import com.boot.dao.Test3Repository;
import com.boot.service.Test2Service;
import com.boot.service.Test3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class Test3ServiceImp implements Test3Service {
    //注入数据仓库
    @Autowired
    Test3Repository test3Repository;
    @Override
    @Transactional

    public List<Test3> findAllTest() {
        return test3Repository.findAll();
    }

    @Override
    public List<Test3> findTestByPage(int index, int size) {
        return test3Repository.selectTest(index,size);
    }

    @Override
    public int getCount() {
            return test3Repository.getCount();
    }
}