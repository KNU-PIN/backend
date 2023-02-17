package com.example.knupin.service;

import com.example.knupin.domain.Comment;
import com.example.knupin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> readComments(int pinId) {
        return null;
    }
}