package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Test2;
import com.boot.bean.po.Test3;
import lombok.Data;

import java.util.List;

@Data
public class Test3ListVo extends HttpResult {
    List<Test3> test3List;
    private int page;
    private int totalPage;
}
