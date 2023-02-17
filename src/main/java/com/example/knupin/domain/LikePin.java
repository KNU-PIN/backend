package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class LikePin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    private String ip;

    private Date createdAt;
}
