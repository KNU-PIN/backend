package com.example.knupin.model.response;

import com.example.knupin.domain.Picture;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponsePictureDTO {
    private String pictureSrc;

    private int sequence;

    public ResponsePictureDTO() {
    }

    public ResponsePictureDTO(Picture picture) {
        this.pictureSrc = picture.getPictureSrc();
        this.sequence = picture.getSequence();
    }
}
