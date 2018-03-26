package com.boot.service.impl;

import com.boot.bean.po.Entrance;
import com.boot.bean.po.Test;
import com.boot.bean.vo.TestStuVo;
import com.boot.core.BusiException;
import com.boot.core.ResultCode;
import com.boot.dao.EntrRepository;
import com.boot.dao.TestRepository;
import com.boot.service.EntrService;
import com.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Integer> answer=testStuVo.getTestAns();
        if(answer.get(29)==null)
        {
            throw new BusiException(ResultCode.QUESTION_UNEXIST);
        }
        int test1= answer.get(4) +answer.get(9)+answer.get(13)+answer.get(17)+answer.get(23)+answer.get(29);  //5、10、14、18、24、30
        int test2= answer.get(2) +answer.get(5)+answer.get(12)+answer.get(19)+answer.get(21)+answer.get(28);  //3、6、13、20、22、29
        int test3= answer.get(1) +answer.get(7)+answer.get(14)+answer.get(16)+answer.get(24)+answer.get(27);  //2、8、15、17、25、28
        int test4= answer.get(0) +answer.get(6)+answer.get(10)+answer.get(15)+answer.get(20)+answer.get(25);  //1、7、11、16、21、26
        int test5= answer.get(3) +answer.get(8)+answer.get(11)+answer.get(18)+answer.get(22)+answer.get(26);  //4、9、12、19、23、27
        entrance.setConformity(test4);
        entrance.setDominance(test1);
        entrance.setExtroversion(test2);
        entrance.setPatience(test3);
        entrance.setSigma(test5);
        entrRepository.save(entrance);
    }

    @Override
    public List<Entrance> findEntrByStudent(String name) {
        List<Entrance> entrances=new ArrayList<>();
        Entrance entrance=entrRepository.findByStudent(name);
        entrances.add(entrance);
        return entrances;
    }
}