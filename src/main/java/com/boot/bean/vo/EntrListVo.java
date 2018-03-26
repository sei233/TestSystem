package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.Entrance;
import com.boot.bean.po.Test;
import lombok.Data;

import java.util.List;

@Data
public class EntrListVo extends HttpResult {
    List<Entrance> entrList;

}
