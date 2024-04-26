package com.reservation.userservice.vo.response.auth;

import lombok.Data;

@Data
public class ResponseVo {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;

}
