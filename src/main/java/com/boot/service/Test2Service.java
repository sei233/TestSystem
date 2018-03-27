package com.boot.service;

import com.boot.bean.po.Test;
import com.boot.bean.po.Test2;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Chenxiang on 2018/1/5.
 */
public interface Test2Service {

    List<Test2> findAllTest() throws BusiException;
    List<Test2> findTestByPage(int index, int size) throws BusiException;
    int getCount()throws BusiException;
}

