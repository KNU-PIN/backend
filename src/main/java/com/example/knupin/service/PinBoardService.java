package com.example.knupin.service;

import com.example.knupin.model.PinBoardDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.knupin.domain.Pin;
import com.example.knupin.repository.PinBoardRepository;

@Service
public class PinBoardService {
    @Autowired
    private PinBoardRepository pinBoardRepository;

    public PinBoardDTO readPinBoard(int pinId){
        return new PinBoardDTO(pinBoardRepository.findByPinId(pinId).get(0));
    }
    
}
