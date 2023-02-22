package com.example.knupin.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RequestSearchPinDTO {
    private String[] pinTypes;
    private String keyword;

    @Builder
    public RequestSearchPinDTO(String[] pinTypes, String keyword){
        this.pinTypes = pinTypes;
        this.keyword = keyword;
    }
}
