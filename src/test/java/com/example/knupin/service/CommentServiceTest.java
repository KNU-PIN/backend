package com.example.knupin.service;

import com.example.knupin.controller.CommentDTO;
import com.example.knupin.domain.Comment;
import com.example.knupin.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void addComment(){
        // given
        CommentDTO params = CommentDTO.builder()
                .pinId(1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        int commentID = commentService.createComment(params);

        // then
        Comment entity = commentRepository.findById(commentID).get();
        assertThat(entity.getIp()).isEqualTo("12.23.45.56");
        assertThat(entity.getContents()).isEqualTo("하이하이");
    }

    @Test
    public void readComments(){
        // given
        CommentDTO params = CommentDTO.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        CommentDTO params2 = CommentDTO.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이2")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        commentService.createComment(params);
        commentService.createComment(params2);

        // then
        List<Comment> comments = commentService.readComments(-1);
        assertThat(comments.size()).isEqualTo(2);
    }

    @Test
    public void countComments(){
        // given
        CommentDTO params = CommentDTO.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        CommentDTO params2 = CommentDTO.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이2")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        commentService.createComment(params);
        commentService.createComment(params2);

        // then
        int commentCnt = commentService.countComments(-1);
        assertThat(commentCnt).isEqualTo(2);
    }

}
