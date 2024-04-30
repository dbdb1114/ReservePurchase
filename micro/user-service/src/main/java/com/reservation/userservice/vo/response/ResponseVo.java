package com.reservation.userservice.vo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;

}
