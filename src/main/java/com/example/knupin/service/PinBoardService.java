package com.example.knupin.service;

import com.example.knupin.model.PinBoardDTO;
import com.example.knupin.domain.Picture;
import com.example.knupin.exception.UploadFailedException;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.WrongPasswordException;
import com.example.knupin.domain.Pin;
import com.example.knupin.repository.PinBoardRepository;
import com.example.knupin.repository.PictureRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

@Service
public class PinBoardService {
    @Autowired
    private PinBoardRepository pinBoardRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private S3Service s3Service;

    public int createPinBoard(PinBoardDTO pinBoardDTO) {
        int pinId = pinBoardRepository.save(pinBoardDTO.toEntity()).getPinId();
        int sequence = 0;
        for(MultipartFile image : pinBoardDTO.getImages()){
            try{
                String pictureSrc = s3Service.uploadFile(image, pinId + "_" + sequence);
                pictureRepository.save(new Picture(pinId, pictureSrc, sequence++));
            }catch (IOException e){
                throw new UploadFailedException("The image upload failed.");
            }
        }

        return pinId;
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