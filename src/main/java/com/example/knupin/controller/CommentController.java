package com.example.knupin.controller;

import com.example.knupin.model.CommentDTO;
import com.example.knupin.model.response.ResponseCommentListDTO;
import com.example.knupin.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
    public void createComment(@RequestBody CommentDTO commentDTO,HttpServletRequest req){
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();
        commentDTO.setIp(ip);
        commentDTO.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime()));
        commentService.createComment(commentDTO);
    }

    @GetMapping("/{pinId}")
    @ResponseBody
    public ResponseCommentListDTO readComments(@PathVariable int pinId, final Pageable pageable){
        return commentService.readComments(pinId, pageable);
    }

}
