package com.boot.service;

import com.boot.bean.po.Test2;
import com.boot.bean.po.Test3;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Chenxiang on 2018/1/5.
 */
public interface Test3Service {

    List<Test3> findAllTest() throws BusiException;
    List<Test3> findTestByPage(int index, int size) throws BusiException;
    int getCount()throws BusiException;
}

