package com.example.knupin.service;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.domain.Comment;

import java.util.List;

public interface CommentService {
    // 댓글 작성
    public int createComment(CommentDTO commentDTO);

    // 댓글 조회
    public List<Comment> readComments(int pinId);

    // 댓글 수 조회
    public int countComments(int pinId);

}
