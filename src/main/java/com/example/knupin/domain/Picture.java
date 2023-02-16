package com.example.knupin.domain;

import jakarta.persistence.*;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int picture_id;

    @Column(nullable = false)
    private int pin_id;

    @Column(nullable = false)
    private String picture_src;

    @Column(nullable = false)
    private int sequence;
}
