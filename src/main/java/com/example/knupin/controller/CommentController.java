package com.example.knupin.controller;

import com.example.knupin.service.CommentService;
import jakarta.websocket.ClientEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/api/v1/comment/create")
    @ResponseBody
    public CommentDTO createComment(@RequestBody Map<String, Object> body){
        CommentDTO commentDTO = CommentDTO.builder()
                .pinId((int)body.get("pinId"))
                .contents(body.get("contents").toString())
                .ip(null)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        // ** client로부터 ip받기 필요 **
        commentService.createComment(commentDTO);
        return commentDTO;
    }


}
