package com.example.knupin.controller;

import com.example.knupin.domain.Pin;
import com.example.knupin.model.request.RequestPinBoardDTO;
import com.example.knupin.model.response.ResponsePinBoardDTO;
import com.example.knupin.service.PinBoardService;
import com.example.knupin.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/v1/pinboard")
public class PinBoardController {
    @Autowired
    private PinBoardService pinBoardService;
    @Autowired
    private S3Service s3Service;

    @GetMapping("/{pinId}")
    @ResponseBody
    public ResponsePinBoardDTO readPinBoard(@PathVariable int pinId){
        System.out.println("readPinBoard "+pinId);
        return pinBoardService.readPinBoard(pinId);
    }

    @PostMapping("/createpin")
    @ResponseBody
    public int readPinBoard(RequestPinBoardDTO body,@RequestHeader("X-FORWARDED-FOR") String ip){
        body.setIp(ip);
        body.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return pinBoardService.createPinBoard(body);
    }
    
    @DeleteMapping("/{pinId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePinBoard(@PathVariable int pinId,@RequestBody Map<String, Object> body) {
        String pw = body.get("pw").toString();
        pinBoardService.deletePinBoard(pinId,pw);
    }
}
