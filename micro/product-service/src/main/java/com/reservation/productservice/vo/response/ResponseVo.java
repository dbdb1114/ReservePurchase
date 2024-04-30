package com.reservation.productservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;

}
