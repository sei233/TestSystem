package com.boot.service;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Entrance2;
import com.boot.bean.vo.TestStuVo;
import com.boot.core.BusiException;

import java.util.List;

/**
 * Created by Shiqian on 2018/3/19.
 */
public interface Entr2Service {
      void calculate(TestStuVo testStuVo, String name,List<Integer> answer)throws BusiException;
      List<Entrance2> findGradesByStudent(String name)throws BusiException;
}

