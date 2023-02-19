package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import lombok.Getter;

@Entity
@Getter
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pinId;

    private String ip;

    private Float latitude; // 위도

    private Float longitude; //경도

    private String title;

    private String contents;

    private String type;

    private String pw;

    private Date createdAt;

    private Boolean isDeleted;
}
