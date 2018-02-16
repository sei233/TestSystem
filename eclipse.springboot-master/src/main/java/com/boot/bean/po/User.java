package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Chenxiang on 2018/1/5.
 * po的意思是，和数据库直接对应的bean
 * @Entity注解表示这个bean是spring data jpa配置的po，Spring会扫描并且配置关系
 * @Table注解是配置对应数据库中的哪张表
 * @Data注解是lombokjar包提供的，加上后会自动帮你生成get和set方法，编译期可能会报错没有getset方法，百度下eclipse有没有
 * @lomok插件，没有的话就不用这个注解，自己用快捷点快速生成get,set方法吧
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_phone")
    private String userPhone;
    //test
}
