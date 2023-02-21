package com.example.knupin.service;

import com.example.knupin.repository.PinBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinSearchService {
    @Autowired
    private PinBoardRepository pinBoardRepository;

    public PinSearchService(PinBoardRepository pinBoardRepository) {
        this.pinBoardRepository = pinBoardRepository;
    }



}
