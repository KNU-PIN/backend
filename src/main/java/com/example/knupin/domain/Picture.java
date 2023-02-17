package com.example.knupin.domain;

import jakarta.persistence.*;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pictureId;

    @Column(nullable = false)
    private int pinId;

    @Column(nullable = false)
    private String pictureSrc;

    @Column(nullable = false)
    private int sequence;
}
