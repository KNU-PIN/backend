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
    private int comment_id;

    private int pin_id;

    private String ip;

    private String contents;

    private Date created_at;

    @Builder
    public Comment(int pin_id, String ip, String contents, Date created_at){
        this.pin_id = pin_id;
        this.ip = ip;
        this.contents = contents;
        this.created_at = created_at;
    }

}
