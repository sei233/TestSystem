package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
@Data
public class Test {
    @Id
    @Column(name = "question")
    private String question;


}