package com.example.knupin.controller;

import com.example.knupin.domain.Comment;
import com.example.knupin.model.CommentDTO;
import com.example.knupin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
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
                .ip("12.11.13.14")
                .build();
        commentService.createComment(commentDTO);
        return commentDTO;
    }

    @GetMapping("/api/v1/comment/{pinId}")
    @ResponseBody
    public Map readComments(@PathVariable int pinId){
        Map result = new HashMap<String, Object>();
        for (Comment comment:commentService.readComments(pinId)) {
            result.put(comment.getCommentId(), comment);
        }
        return result;
    }

}
