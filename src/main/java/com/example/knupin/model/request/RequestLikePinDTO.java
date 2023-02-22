package com.example.knupin.model.request;

import com.example.knupin.domain.LikePin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestLikePinDTO {
    private int pinId;
    private String ip;
    private Date createdAt;

    public RequestLikePinDTO() {
    }

    public LikePin toEntity() {
        return LikePin.builder()
                .pinId(pinId)
                .ip(ip)
                .createdAt(createdAt)
                .build();
    }
}
