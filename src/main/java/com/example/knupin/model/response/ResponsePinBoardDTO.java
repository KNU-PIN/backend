package com.example.knupin.model.response;

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
public class ResponsePinBoardDTO {

    private Float latitude; // 위도

    private Float longitude; //경도

    private String title;

    private String contents;

    private String type;

    private List<ResponsePictureDTO> images;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdAt;

    private int like;

    public ResponsePinBoardDTO() {
    }

    public ResponsePinBoardDTO(Pin pin) {
        this.latitude = pin.getLatitude()/10000f;
        this.longitude = pin.getLongitude()/10000f;
        this.title = pin.getTitle();
        this.contents = pin.getContents();
        this.type = pin.getType();
        this.createdAt = pin.getCreatedAt();
    }

}
