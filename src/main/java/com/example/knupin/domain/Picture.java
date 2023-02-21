package com.example.knupin.domain;

import jakarta.persistence.*;
import lombok.Builder;

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

    @Builder
    public Picture(int pinId, String pictureSrc, int sequence) {
        this.pinId = pinId;
        this.pictureSrc = pictureSrc;
        this.sequence = sequence;
    }
}
