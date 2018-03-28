package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qanda")
@Data
public class Test3 {
    @Id
    @Column(name = "question")
    private String question;
    //专业能力测试之判断
}