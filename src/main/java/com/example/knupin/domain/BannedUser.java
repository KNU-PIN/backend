package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class BannedUser {
    @Id
    private String ip;

    private Date banned_date;

    private String contents;
}
