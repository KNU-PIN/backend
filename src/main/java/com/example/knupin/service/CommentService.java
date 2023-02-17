package com.example.knupin.service;

import com.example.knupin.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    // 댓글 작성
    public void addComment(Comment comment);

    // 댓글 조회
    public List<Comment> readComments(int pinId);


}
