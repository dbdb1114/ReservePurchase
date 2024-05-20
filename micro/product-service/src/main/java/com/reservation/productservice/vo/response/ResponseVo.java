package com.reservation.productservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo<T> {

    public ResponseVo(ResponseStatus responseStatus) {
        this.responseMessage = responseStatus.message;
        this.responseCode = responseStatus.name();
    }

    String responseCode;
    String responseMessage;
    T data;
}
