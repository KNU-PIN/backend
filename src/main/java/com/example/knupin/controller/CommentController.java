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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    @ResponseBody
    public void createComment(@RequestBody Map<String, Object> body){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();

        CommentDTO commentDTO = CommentDTO.builder()
                .pinId((int)body.get("pinId"))
                .contents(body.get("contents").toString())
                .ip(ip)
                .build();
        commentDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        commentService.createComment(commentDTO);
    }

    @GetMapping("/{pinId}")
    @ResponseBody
    public ResponseCommentListDTO readComments(@PathVariable int pinId, final Pageable pageable){
        return commentService.readComments(pinId, pageable);
    }

}
