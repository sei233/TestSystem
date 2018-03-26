package com.boot.service;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Test;
import com.boot.bean.vo.TestStuVo;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Shiqian on 2018/3/19.
 */
public interface EntrService {
      void calculate(TestStuVo testStuVo,String name)throws BusiException;
      List<Entrance> findEntrByStudent(String name)throws BusiException;
}

