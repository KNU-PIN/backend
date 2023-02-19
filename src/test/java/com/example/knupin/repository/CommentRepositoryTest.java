package com.example.knupin.repository;

import com.example.knupin.domain.Comment;
import com.example.knupin.service.CommentService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void save(){
        // given
        Comment params = Comment.builder()
                .pinId(1)
                .ip("12.23.45.56")
                .contents("하이하이")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        commentRepository.save(params);

        // then
        Comment entity = commentRepository.findById(params.getCommentId()).get();
        assertThat(entity.getIp()).isEqualTo("12.23.45.56");
        assertThat(entity.getContents()).isEqualTo("하이하이");
    }

    @Test
    public void findByPinId(){
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
        commentRepository.save(params);
        commentRepository.save(params2);

        // then
        List<Comment> comments = commentRepository.findByPinId(-1);
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
        commentRepository.save(params);
        commentRepository.save(params2);

        // then
        int commentCount = commentRepository.countByPinId(-1);
        assertThat(commentCount).isEqualTo(2);
    }

}
