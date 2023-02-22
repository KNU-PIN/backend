package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import lombok.Builder;

import java.util.Date;

@Entity
public class LikePin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    private int pinId;

    private String ip;

    private Date createdAt;

    public LikePin() {
    }

    @Builder
    public LikePin(int pinId, String ip, Date createdAt) {
        this.pinId = pinId;
        this.ip = ip;
        this.createdAt = createdAt;
    }
}
