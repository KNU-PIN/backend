package com.example.knupin.service;

import com.example.knupin.domain.Comment;
import com.example.knupin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> readComments(int pinId) {
        return commentRepository.findByPinId(pinId);
    }

    @Override
    public int countComments(int pinId) {
        return commentRepository.countByPinId(pinId);
    }
}
