package com.example.knupin.service;

import com.example.knupin.model.request.RequestSearchPinDTO;
import com.example.knupin.repository.PinBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PinSearchService {
    @Autowired
    private PinBoardRepository pinBoardRepository;

    public PinSearchService(PinBoardRepository pinBoardRepository) {
        this.pinBoardRepository = pinBoardRepository;
    }

    public Object searchPin(RequestSearchPinDTO requestSearchPinDTO){

        // 해당하는 pinId 리스트 가져오기
        List<Integer> pinIdList = getPinIdList(requestSearchPinDTO.getKeyword());

        // pinID 별로 조회해서 type에 해당하는 id만 따로 걸러내기
        for(int pinId: pinIdList){
            if(true){  // pin의 type이 typelist에 있으면
                //결과리스트에 해당 pin에 대한 정보 담기(이미지, 위치)
            }
        }
        return null;
    }

    /**
     * keyword 받아서 DB에서 검색 후 해당하는 pinId 리스트에 담아서 반환
     * @return
     */
    private List<Integer> getPinIdList(String keyword){
        List<Integer> pinIdList = new ArrayList<>();

        // db에서 검색
        for(int i=0; i<=20; i++){
            for(int j=0; j<=20; j++){

            }
        }
        return pinIdList;
    }



}
