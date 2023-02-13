package com.example.knupin.domain;

import jakarta.persistence.*;

@Entity
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int img_id;

    @Column(nullable = false)
    private int pin_id;

    @Column(nullable = false)
    private String img_src;

    @Column(nullable = false)
    private int index;
}
