package com.reservation.orderservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;

}
