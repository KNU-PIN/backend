package com.example.knupin.service;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.domain.Comment;
import com.example.knupin.model.ResponseCommentDTO;
import com.example.knupin.model.ResponseCommentListDTO;
import com.example.knupin.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ResponseCommentListDTO readComments(int pinId, final Pageable pageable) {
        List<Comment> comments = commentRepository.findByPinId(pinId, pageable);
        ResponseCommentListDTO responseCommentListDTO = ResponseCommentListDTO.builder()
                .comments(anonymization(comments, pageable))
                .build();
        return responseCommentListDTO;
    }

    @Override
    public int countComments(int pinId) {
        return commentRepository.countByPinId(pinId);
    }

    private List<ResponseCommentDTO> anonymization(List<Comment> comments, Pageable pageable){
        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<ResponseCommentDTO>();
        int startNum = pageable.getPageNumber() * pageable.getPageSize();
        for(Comment comment: comments){
            ResponseCommentDTO responseCommentDTO = ResponseCommentDTO.builder()
                            .name("익명"+ startNum++)
                                    .contents(comment.getContents())
                                            .createdAt(comment.getCreatedAt())
                                                    .build();
            responseCommentDTOS.add(responseCommentDTO);
        }
        return responseCommentDTOS;
    }

}
