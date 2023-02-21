package com.example.knupin.model;

import com.example.knupin.domain.Picture;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Getter(AccessLevel.PUBLIC)
@Setter
@ToString
public class PictureDTO {
    private int pinId;

    private String pictureSrc;

    private int sequence;

    public PictureDTO() {
    }

    public PictureDTO(int pinId, int sequence) {
        this.pinId = pinId;
        this.sequence = sequence;
    }

    public Picture toEntity() {
        return Picture.builder()
                .pinId(pinId)
                .pictureSrc(pictureSrc)
                .sequence(sequence)
                .build();
    }
}
