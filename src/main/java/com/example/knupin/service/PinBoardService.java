package com.example.knupin.service;

import com.example.knupin.model.request.RequestPinBoardDTO;
import com.example.knupin.model.response.ResponsePinBoardDTO;
import com.example.knupin.model.response.ResponsePictureDTO;
import com.example.knupin.domain.Picture;
import com.example.knupin.domain.Pin;
import com.example.knupin.exception.UploadFailedException;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.WrongPasswordException;
import com.example.knupin.repository.PinBoardRepository;
import com.example.knupin.repository.PictureRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class PinBoardService {
    @Autowired
    private PinBoardRepository pinBoardRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private S3Service s3Service;

    public int createPinBoard(RequestPinBoardDTO pinBoardDTO) {
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

    public ResponsePinBoardDTO readPinBoard(int pinId) {
        Optional<Pin> optionalPin = pinBoardRepository.findById(pinId);
        if (!optionalPin.isPresent()){
            throw new PinNotFoundException("The pin does not exist.");
        }
        Pin pin = optionalPin.get();
        if (pin.getIsDeleted()){
            throw new PinDeletedException("The pin has been deleted.");
        }
        ResponsePinBoardDTO responsePinBoardDTO= new ResponsePinBoardDTO(pin);
        responsePinBoardDTO.setImages(
            pictureRepository
            .findByPinId(pinId)
            .stream()
            .map(ResponsePictureDTO::new)
            .collect(Collectors.toList())
        );
        //responsePinBoardDTO.setLike();
        return responsePinBoardDTO;
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