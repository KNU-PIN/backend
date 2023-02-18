package com.example.knupin.service;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.domain.Comment;
import com.example.knupin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public int createComment(CommentDTO commentDTO) {
        commentDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return commentRepository.save(commentDTO.toEntity()).getCommentId();
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
