package com.example.knupin.controller;

import com.example.knupin.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CommentDTO {

    private int pinId;

    private String ip;

    private String contents;

    private Date createdAt;

    @Builder
    public CommentDTO(int pinId, String ip, String contents, Date createdAt){
        this.pinId = pinId;
        this.ip = ip;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public Comment toEntity(){
        return Comment.builder()
                .pinId(pinId)
                .ip(ip)
                .contents(contents)
                .createdAt(createdAt)
                .build();
    }
}
