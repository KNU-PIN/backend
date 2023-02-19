package com.example.knupin.model;

import com.example.knupin.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseCommentListDTO {
    private List<ResponseCommentDTO> comments;

    @Builder
    public ResponseCommentListDTO(List<ResponseCommentDTO> comments){
        this.comments = comments;
    }
}
