package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testjudge")
@Data
public class Test2 {
    @Id
    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private int answer;
    //专业能力测试之判断
}