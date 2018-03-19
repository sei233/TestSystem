package com.boot.bean.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entrance")
@Data
public class Entrance {
        @Id
        @Column(name = "student")
        private String student;
        @Column(name = "dominance")
        private int dominance;
        @Column(name = "extroversion")
        private int extroversion;
        @Column(name = "patience")
        private int patience;
        @Column(name = "conformity")
        private int conformity;
        @Column(name = "sigma")
        private int sigma;
    }

