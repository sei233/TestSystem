package com.boot.service;

import com.boot.bean.po.Test;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Chenxiang on 2018/1/5.
 */
public interface TestService {

    List<Test> findAllTest() throws BusiException;
    List<Test> findTestByPage(int index,int size) throws BusiException;
    int getCount()throws BusiException;
}

