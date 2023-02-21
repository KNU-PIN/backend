package com.example.knupin.service;

import com.example.knupin.model.PinBoardDTO;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.WrongPasswordException;
import com.example.knupin.domain.Pin;
import com.example.knupin.repository.PinBoardRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PinBoardService {
    @Autowired
    private PinBoardRepository pinBoardRepository;

    public int createPinBoard(PinBoardDTO pinBoardDTO) {

        return pinBoardRepository.save(pinBoardDTO.toEntity()).getPinId();
    }

    public PinBoardDTO readPinBoard(int pinId) {
        Optional<Pin> optionalPin = pinBoardRepository.findById(pinId);
        if (!optionalPin.isPresent()){
            throw new PinNotFoundException("The pin does not exist.");
        }
        Pin pin = optionalPin.get();
        if (pin.getIsDeleted()){
            throw new PinDeletedException("The pin has been deleted.");
        }
        return new PinBoardDTO(pin);
    }

    public void deletePinBoard(int pinId,String pw) {
        Optional<Pin> optionalPin = pinBoardRepository.findById(pinId);
        if (!optionalPin.isPresent()){
            throw new PinNotFoundException("The pin does not exist.");
        }
        Pin pin = optionalPin.get();
        if (pin.getIsDeleted()){
            throw new PinDeletedException("The pin has already been deleted.");
        }
        if (!pin.getPw().equals(pw)){
            throw new WrongPasswordException("The password is wrong.");
        }
        pin.setIsDeleted(true);
        pinBoardRepository.save(pin);
    }
}