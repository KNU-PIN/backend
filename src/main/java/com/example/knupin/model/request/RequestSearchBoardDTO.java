package com.example.knupin.model.request;

import com.example.knupin.domain.Pin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class RequestSearchBoardDTO {

    private String[] types;

    private Float latitude;

    private Float longitude;

    private String keyword;


    @Builder
    public RequestSearchBoardDTO(String[] types, Float latitude, Float longitude, String keyword) {
        this.types = types;
        this.latitude = latitude;
        this.longitude = longitude;
        this.keyword = keyword;
    }
}
