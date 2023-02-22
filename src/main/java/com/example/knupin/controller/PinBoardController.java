package com.example.knupin.controller;


import com.example.knupin.model.SearchPinInterface;
import com.example.knupin.model.request.RequestSearchBoardDTO;
import com.example.knupin.model.request.RequestSearchPinDTO;
import com.example.knupin.model.response.ResponseSearchBoardDTO;
import com.example.knupin.model.response.ResponseSearchPinDTO;
import com.example.knupin.domain.Pin;
import com.example.knupin.model.request.RequestPinBoardDTO;
import com.example.knupin.model.response.ResponsePinBoardDTO;

import com.example.knupin.service.PinBoardService;
import com.example.knupin.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;


import java.time.ZoneId;
import java.util.*;
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
    public int createPinBoard(RequestPinBoardDTO body,@RequestHeader("X-FORWARDED-FOR") String ip){
        body.setIp(ip);
        body.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime()));
        return pinBoardService.createPinBoard(body);
    }
    
    @DeleteMapping("/{pinId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePinBoard(@PathVariable int pinId,@RequestBody Map<String, Object> body) {
        String pw = body.get("pw").toString();
        pinBoardService.deletePinBoard(pinId,pw);
    }

    @GetMapping("/searchpin")
    @ResponseBody
    public HashMap<Integer, ResponseSearchPinDTO> searchPin(String[] types, String keyword){
        RequestSearchPinDTO requestSearchPinDTO = RequestSearchPinDTO.builder()
                .pinTypes(types)
                .keyword(keyword)
                .build();

        return pinBoardService.searchPin(requestSearchPinDTO);
    }

    @GetMapping("/searchboard")
    @ResponseBody
    public List<ResponseSearchBoardDTO> searchBoard(String[] types, Float latitude, Float longitude, String keyword){
        RequestSearchBoardDTO requestSearchBoardDTO = RequestSearchBoardDTO.builder()
                .types(types)
                .latitude(latitude)
                .longitude(longitude)
                .keyword(keyword)
                .build();
        return pinBoardService.searchBoard(requestSearchBoardDTO);
    }
}
