package com.example.knupin.service;

import com.example.knupin.domain.Pin;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.model.CommentDTO;
import com.example.knupin.domain.Comment;
import com.example.knupin.model.PinBoardDTO;
import com.example.knupin.model.ResponseCommentDTO;
import com.example.knupin.model.ResponseCommentListDTO;
import com.example.knupin.repository.CommentRepository;
import com.example.knupin.repository.PinBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private PinBoardRepository pinBoardRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PinBoardRepository pinBoardRepository) {
        this.commentRepository = commentRepository;
        this.pinBoardRepository = pinBoardRepository;
    }


    @Override
    @Transactional
    public int createComment(CommentDTO commentDTO) {
        Optional<Pin> optionalPin = pinBoardRepository.findById(commentDTO.getPinId());
        if (optionalPin.isPresent()) {
            Pin pin = optionalPin.get();
            if (pin.getIsDeleted()) {
                throw new PinDeletedException("The pin has been deleted.");
            }
            return commentRepository.save(commentDTO.toEntity()).getCommentId();
        } else {
            throw new PinNotFoundException("The pin does not exist.");
        }
    }

    @Override
    public ResponseCommentListDTO readComments(int pinId, final Pageable pageable) {
        Optional<Pin> optionalPin = pinBoardRepository.findById(pinId);
        if (optionalPin.isPresent()) {
            Pin pin = optionalPin.get();
            if (pin.getIsDeleted()) {
                throw new PinDeletedException("The pin has been deleted.");
            }

            List<Comment> comments = commentRepository.findByPinId(pinId, pageable);
            ResponseCommentListDTO responseCommentListDTO = ResponseCommentListDTO.builder()
                    .comments(anonymization(comments, pageable))
                    .build();
            return responseCommentListDTO;

        } else {
            throw new PinNotFoundException("The pin does not exist.");
        }
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
