package com.example.knupin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    public void addComment(){

    }

    @Test
    public void readComment(){

    }

}
