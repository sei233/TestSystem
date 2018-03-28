package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entrance2")
@Data
public class Entrance2 {
        @Id
        @Column(name = "student")
        private String student;
        @Column(name = "grades")
        private int grades;
    }

