package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Entity
@Getter
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pinId;

    private String ip;

    private int latitude; // 위도

    private int longitude; //경도

    private String title;

    private String contents;

    private String type;

    private String pw;

    private Date createdAt;

    @Setter
    private Boolean isDeleted;

    public Pin() {
    }

    @Builder
    public Pin(String ip, int latitude, int longitude, String title, String contents, String type, String pw, Date createdAt) {
        this.ip = ip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.contents = contents;
        this.type = type;
        this.pw = pw;
        this.createdAt = createdAt;
        this.isDeleted = false;
    }
}
