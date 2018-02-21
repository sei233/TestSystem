package com.boot.bean.vo;

import lombok.Data;

/**
 * Created by Chenxiang on 2018/1/5.
 * @Vo是对应的视图，也就是你接收的页面发来的消息bean，和你要返回的bean
 * @原则上要和你页面传来的数据一致
 */
@Data
public class UserQueryVo {
    private int index=0;
    private int size=1;
}
