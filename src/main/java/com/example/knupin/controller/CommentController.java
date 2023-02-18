package com.example.knupin.controller;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.model.ResponseCommentListDTO;
import com.example.knupin.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


//    @PostMapping("/api/v1/comment/create")
//    @ResponseBody
//    public CommentDTO createComment(@RequestBody Map<String, Object> body){
//        CommentDTO commentDTO = CommentDTO.builder()
//                .pinId((int)body.get("pinId"))
//                .contents(body.get("contents").toString())
//                .ip("12.11.13.14")
//                .build();
//        commentService.createComment(commentDTO);
//        return commentDTO;
//    }

    @PostMapping("/api/v1/comment/create")
    @ResponseBody
    public CommentDTO createComment(@RequestBody Map<String, Object> body){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();

        CommentDTO commentDTO = CommentDTO.builder()
                .pinId((int)body.get("pinId"))
                .contents(body.get("contents").toString())
                .ip(ip)
                .build();
        commentService.createComment(commentDTO);
        return commentDTO;
    }

    @GetMapping("/api/v1/comment/{pinId}")
    @ResponseBody
    public ResponseCommentListDTO readComments(@PathVariable int pinId, final Pageable pageable){
        return commentService.readComments(pinId, pageable);
    }

}
