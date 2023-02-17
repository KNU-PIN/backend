package com.example.knupin.service;

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
        Comment params = Comment.builder()
                .pinId(1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        commentService.createComment(params);

        // then
        Comment entity = commentRepository.findById(params.getCommentId()).get();
        assertThat(entity.getIp()).isEqualTo("12.23.45.56");
        assertThat(entity.getContents()).isEqualTo("하이하이");
    }

    @Test
    public void readComments(){
        // given
        Comment params = Comment.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Comment params2 = Comment.builder()
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
        Comment params = Comment.builder()
                .pinId(-1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Comment params2 = Comment.builder()
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
