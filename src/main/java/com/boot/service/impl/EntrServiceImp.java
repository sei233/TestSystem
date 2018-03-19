package com.boot.service.impl;

import com.boot.bean.po.Test;
import com.boot.dao.EntrRepository;
import com.boot.dao.TestRepository;
import com.boot.service.EntrService;
import com.boot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by ShiQian on 2018/1/5.
 */
@Service
public class EntrServiceImp implements EntrService {
    //注入数据仓库
    @Autowired
    EntrRepository entrRepository;


}