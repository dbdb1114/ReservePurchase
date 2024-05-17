package com.reservation.orderservice.vo.response;

import lombok.Data;

@Data
public class ResponseVo<T> {


    private static final long serialVersionUID = 1L;

    private ResponseVo() {}


    public ResponseVo(ResponseStatus status) {
        this.responseCode = status.responseCode;
        this.responseMessage = status.responseMessage;
    }

    public ResponseVo setData(T data){
        this.data = data;
        return this;
    }

    String responseCode;
    String responseMessage;
    T data;
}
