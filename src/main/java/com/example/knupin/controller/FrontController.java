package com.example.knupin.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FrontController implements ErrorController {
    @GetMapping({"/", "error"})
    public String index(){
        return "index.html";
    }

    @RequestMapping(value = "/error")
    public String handleNoHandleFoundException(HttpServletResponse response, HttpServletRequest request) {
        return "index";
    }
}