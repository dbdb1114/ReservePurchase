package com.reservation.productservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo<T> {

    public ResponseVo() {}

    String responseCode;
    String responseMessage;
    T data;
}
