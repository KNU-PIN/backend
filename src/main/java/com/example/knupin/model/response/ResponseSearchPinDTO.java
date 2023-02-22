package com.example.knupin.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseSearchPinDTO {

    private float latitude;

    private float longitude;

    private String type;

    private String img_src;

    @Builder
    public ResponseSearchPinDTO(float latitude, float longitude, String type, String img_src) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.img_src = img_src;
    }
}
