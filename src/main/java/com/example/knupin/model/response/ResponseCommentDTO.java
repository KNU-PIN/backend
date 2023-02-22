package com.example.knupin.model.response;

import com.example.knupin.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    @Builder
    public ResponseCommentDTO(String name, String contents, Date createdAt){
        this.name = name;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
