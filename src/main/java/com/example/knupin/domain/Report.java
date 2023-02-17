package com.example.knupin.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Report {
    @Id
    private int pinId;

    private int reportCount;

    private Boolean isIgnored;
}
