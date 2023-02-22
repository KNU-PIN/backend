package com.example.knupin.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResponseSearchBoardDTO {
    private int pinId;
    private String title;
    private String contents;
    private int likeCnt;
    private int commentCnt;
    private Date createdAt;
    private String imgSrc;

    @Builder
    public ResponseSearchBoardDTO(int pinId, String title, String contents, int likeCnt, int commentCnt, Date createdAt, String imgSrc) {
        this.pinId = pinId;
        this.title = title;
        this.contents = contents;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.createdAt = createdAt;
        this.imgSrc = imgSrc;
    }
}
