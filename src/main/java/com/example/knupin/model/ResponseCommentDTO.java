package com.example.knupin.model;

import com.example.knupin.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ResponseCommentDTO {
    private String name;
    private String contents;
    private Date createdAt;

    @Builder
    public ResponseCommentDTO(String name, String contents, Date createdAt){
        this.name = name;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
