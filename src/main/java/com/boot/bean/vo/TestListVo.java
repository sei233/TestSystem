package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Test;
import lombok.Data;
import java.util.List;

@Data
public class TestListVo extends HttpResult {
    List<Test> testList;
    private int page;
    private int totalPage;
}
