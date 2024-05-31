package com.reservation.productservice.vo.response.status;

import lombok.Data;

@Data
public class ResponseVo<T> {

    public ResponseVo(ResponseStatus responseStatus) {
        this.responseMessage = responseStatus.message;
        this.responseCode = responseStatus.name();
    }
    public ResponseVo(ResponseStatus responseStatus, T data) {
        this.responseMessage = responseStatus.message;
        this.responseCode = responseStatus.name();
        this.data = data;
    }

    String responseCode;
    String responseMessage;
    T data;
}
