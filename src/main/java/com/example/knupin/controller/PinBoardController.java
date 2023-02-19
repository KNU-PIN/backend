package com.example.knupin.controller;

import com.example.knupin.domain.Pin;
import com.example.knupin.model.PinBoardDTO;
import com.example.knupin.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/pinboard")
public class PinBoardController {
    @Autowired
    private PinBoardService pinBoardService;

    @GetMapping("/{pinId}")
    @ResponseBody
    public PinBoardDTO readPinBoard(@PathVariable int pinId){
        System.out.println("readPinBoard "+pinId);
        return pinBoardService.readPinBoard(pinId);
    }
    
    @PostMapping("/createpin")
    @ResponseBody
    public Object createPinBoard(@RequestBody PinBoardDTO body){
        System.out.println("createPinBoard "+body);
        return body;
        // PinBoardDTO pinBoardDTO = PinBoardDTO.builder()
        //         .title(body.get("title").toString())
        //         .contents(body.get("contents").toString())
        //         .build();
        // pinBoardService.createPinBoard(pinBoardDTO);
        // return pinBoardDTO;
    }

}
