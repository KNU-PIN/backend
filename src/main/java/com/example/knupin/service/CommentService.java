package com.example.knupin.service;

import com.example.knupin.controller.CommentDTO;
import com.example.knupin.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    // 댓글 작성
    public int createComment(CommentDTO commentDTO);

    // 댓글 조회
    public List<Comment> readComments(int pinId);

    // 댓글 수 조회
    public int countComments(int pinId);

}
