package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    private int pinId;

    private String ip;

    private String contents;

    private Date createdAt;

    @Builder
    public Comment(int pinId, String ip, String contents, Date createdAt){
        this.pinId = pinId;
        this.ip = ip;
        this.contents = contents;
        this.createdAt = createdAt;
    }

}
