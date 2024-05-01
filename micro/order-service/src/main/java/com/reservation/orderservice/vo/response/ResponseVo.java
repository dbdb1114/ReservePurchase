package com.reservation.orderservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo<T> {


    private static final long serialVersionUID = 1L;

    public ResponseVo() {}

    String responseCode;
    String responseMessage;
    T data;
}
