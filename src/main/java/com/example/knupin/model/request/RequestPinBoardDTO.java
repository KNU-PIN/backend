package com.example.knupin.model.request;

import com.example.knupin.domain.Pin;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
public class RequestPinBoardDTO {
    private String ip;

    private Float latitude; // 위도

    private Float longitude; //경도

    private String title;

    private String contents;

    private String type;

    private String pw;

    private List<MultipartFile> images;
    
    private Date createdAt;


    public RequestPinBoardDTO() {
    }

    public Pin toEntity() {
        return Pin.builder()
                .ip(ip)
                .latitude(latitude)
                .longitude(longitude)
                .title(title)
                .contents(contents)
                .type(type)
                .pw(pw)
                .createdAt(createdAt)
                .build();
    }
}
