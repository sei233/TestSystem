package com.boot.service.impl;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Entrance2;
import com.boot.bean.vo.TestStuVo;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.dao.Entr2Repository;
import com.boot.dao.EntrRepository;
import com.boot.service.Entr2Service;
import com.boot.service.EntrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class Entr2ServiceImp implements Entr2Service {
    //注入数据仓库
    @Autowired
    Entr2Repository entr2Repository;
    @Override
    @Transactional
    public void calculate(TestStuVo testStuVo,String name,List<Integer> answer) throws BusiException {
        Entrance2 entrance=new Entrance2();
        entrance.setStudent(name);
        List<Integer> stu_answer=testStuVo.getTestAns();
        if(stu_answer.get(14)==null)
        {
            throw new BusiException(ResultCode.QUESTION_UNEXIST);
        }

        int grades=0;
        for (int i=0;i<stu_answer.size();i++){
            if(stu_answer.get(i)==answer.get(i)) grades++;
        }
        entrance.setGrades(grades);
        //10100   01110  00001
//        entrance.setGrades();
        entr2Repository.save(entrance);
    }

    @Override
    public List<Entrance2> findEntrByStudent(String name) {
        List<Entrance2> entrances=new ArrayList<>();
        Entrance2 entrance=entr2Repository.findByStudent(name);
        entrances.add(entrance);
        return entrances;
    }
}