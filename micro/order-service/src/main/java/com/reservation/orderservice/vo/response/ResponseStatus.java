package com.reservation.orderservice.vo.response;

import org.springframework.util.ObjectUtils;

public enum ResponseStatus {

    SU("Success"),
    FA("Fail"),
    NS("NotEnoughStock"),
    EC("EmptyContents");

    ResponseStatus(String message){
        this.responseCode = this.name();
        this.responseMessage = message;
    }

    final String responseCode;
    final String responseMessage;
}
