package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Test;
import lombok.Data;
import java.util.List;

@Data
public class TestVo extends HttpResult {
    List<Test> testList;

}
