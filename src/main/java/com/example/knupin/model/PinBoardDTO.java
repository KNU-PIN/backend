package com.example.knupin.model;

import com.example.knupin.domain.Pin;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter(AccessLevel.PUBLIC)
@Setter
@ToString
public class PinBoardDTO {
    private String ip;

    private Float latitude; // 위도

    private Float longitude; //경도

    private String title;

    private String contents;

    private String type;

    @Getter(AccessLevel.NONE)
    private String pw;

    private List<MultipartFile> images;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;


    public PinBoardDTO() {
    }

    public PinBoardDTO(Pin pin) {
        this.ip = pin.getIp();
        this.latitude = pin.getLatitude();
        this.longitude = pin.getLongitude();
        this.title = pin.getTitle();
        this.contents = pin.getContents();
        this.type = pin.getType();
        this.pw = pin.getPw();
        this.createdAt = pin.getCreatedAt();
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
