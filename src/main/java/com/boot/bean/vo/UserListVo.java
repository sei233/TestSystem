package com.boot.bean.vo;

import com.boot.bean.base.HttpResult;
import com.boot.bean.po.User;
import lombok.Data;
import java.util.List;

@Data
public class UserListVo extends HttpResult {
    List<User> usersList;
    private int page;
    private int totalPage;
}
