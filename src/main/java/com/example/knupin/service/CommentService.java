package com.example.knupin.service;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.model.response.ResponseCommentListDTO;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    // 댓글 작성
    public int createComment(CommentDTO commentDTO);

    // 댓글 조회
    public ResponseCommentListDTO readComments(int pinId, final Pageable pageable);

    // 댓글 수 조회
    public int countComments(int pinId);

}
