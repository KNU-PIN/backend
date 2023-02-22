package com.example.knupin.service;

import com.example.knupin.model.request.RequestPinBoardDTO;
import com.example.knupin.model.request.RequestLikePinDTO;
import com.example.knupin.model.request.RequestSearchPinDTO;
import com.example.knupin.model.response.ResponsePinBoardDTO;
import com.example.knupin.model.response.ResponsePictureDTO;
import com.example.knupin.model.response.ResponseSearchPinDTO;
import com.example.knupin.model.SearchPinInterface;
import com.example.knupin.domain.Picture;
import com.example.knupin.domain.Pin;
import com.example.knupin.domain.LikePin;
import com.example.knupin.exception.UploadFailedException;
import com.example.knupin.exception.PinNotFoundException;
import com.example.knupin.exception.PinDeletedException;
import com.example.knupin.exception.WrongPasswordException;
import com.example.knupin.exception.LikePinAlreadyExistException;

import com.example.knupin.repository.PinBoardRepository;
import com.example.knupin.repository.PictureRepository;
import com.example.knupin.repository.LikePinRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class PinBoardService {

    private final int LATITUDE_START = 358804;
    private final int LONGITUDE_START = 1286000;

    @Autowired
    private PinBoardRepository pinBoardRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private LikePinRepository likePinRepository;
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
        responsePinBoardDTO.setLike(
            likePinRepository
            .countByPinId(pinId)
        );
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


    /**
     * divide the map area into 20*20
     * get most recommended pin from divided area
     * which has key word and same type from request
     * @param requestSearchPinDTO
     * @return searchPin list(pinId, latitude, longitude, type)
     */
    public HashMap<Integer, ResponseSearchPinDTO> searchPin(RequestSearchPinDTO requestSearchPinDTO) {
        HashMap<Integer, ResponseSearchPinDTO> responseSearchPinDTOList = new HashMap<>();
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
                            .type(searchPin.getType())
                            .latitude(searchPin.getLatitude()/10000f)
                            .longitude(searchPin.getLongitude()/10000f)
                            .img_src(getPictureSrc(searchPin.getPinId()))
                            .build();
                    responseSearchPinDTOList.put(searchPin.getPinId(), responseSearchPinDTO);
                }

            }
        }
        return responseSearchPinDTOList;
    }


    private String getPictureSrc(int pinId){
        List<Picture> pictureList = pictureRepository.findByPinId(pinId);
        if(!pictureList.isEmpty()){
            return pictureList.get(0).getPictureSrc();
        }
        return null;
    }

    public int ddabong(RequestLikePinDTO likePinDTO){
        Optional<Pin> optionalPin = pinBoardRepository.findById(likePinDTO.getPinId());
        if (!optionalPin.isPresent()){
            throw new PinNotFoundException("The pin does not exist.");
        }
        Pin pin = optionalPin.get();
        if (pin.getIsDeleted()){
            throw new PinDeletedException("The pin has already been deleted.");
        }
        Optional<LikePin> optionalLikePin = likePinRepository.findByPinIdAndIp(likePinDTO.getPinId(), likePinDTO.getIp());
        if(optionalLikePin.isPresent()){
            throw new LikePinAlreadyExistException("The like pin already exist.");
        }
        likePinRepository.save(likePinDTO.toEntity());
        return likePinRepository.countByPinId(likePinDTO.getPinId());
    }
}