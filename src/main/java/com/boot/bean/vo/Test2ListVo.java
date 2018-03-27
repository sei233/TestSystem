package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Test;
import com.boot.bean.po.Test2;
import lombok.Data;

import java.util.List;

@Data
public class Test2ListVo extends HttpResult {
    List<Test2> test2List;
    private int page;
    private int totalPage;
}
