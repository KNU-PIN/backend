package com.example.knupin.service;

import com.example.knupin.model.PinBoardDTO;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.WrongPasswordException;
import com.example.knupin.domain.Pin;
import com.example.knupin.model.SearchPinInterface;
import com.example.knupin.model.request.RequestSearchPinDTO;
import com.example.knupin.model.response.ResponseSearchPinDTO;
import com.example.knupin.repository.PinBoardRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PinBoardService {

    private final int LATITUDE_START = 358804;
    private final int LONGITUDE_START = 1286000;

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


    /**
     * divide the map area into 20*20
     * get most recommended pin from divided area
     * which has key word and same type from request
     * @param requestSearchPinDTO
     * @return searchPin list(pinId, latitude, longitude, type)
     */
    public List<ResponseSearchPinDTO> searchPin(RequestSearchPinDTO requestSearchPinDTO) {
        List<ResponseSearchPinDTO> responseSearchPinDTOList = new ArrayList<>();
        String keyword = requestSearchPinDTO.getKeyword();

        for(int i=0; i<200; i+=10){
            for(int j=0; j<200; j+=10){

                Optional<SearchPinInterface> searchPinInterface = pinBoardRepository.searchPin
                        (keyword,
                                LATITUDE_START+i,LATITUDE_START+i+10,
                                LONGITUDE_START+j,LONGITUDE_START+j+10 );

                if(searchPinInterface.isPresent() &&
                        Arrays.stream(requestSearchPinDTO.getPinTypes()).toList().contains(searchPinInterface.get().getType())
                ){
                    SearchPinInterface searchPin = searchPinInterface.get();
                    ResponseSearchPinDTO responseSearchPinDTO = ResponseSearchPinDTO.builder()
                            .pinId(searchPin.getPinId())
                            .type(searchPin.getType())
                            .latitude(searchPin.getLatitude()/10000f)
                            .longitude(searchPin.getLongitude()/10000f)
                            .img_src(null)
                            .build();
                    responseSearchPinDTOList.add(responseSearchPinDTO);
                }

            }
        }
        return responseSearchPinDTOList;
    }
}