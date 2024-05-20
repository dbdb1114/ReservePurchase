package com.reservation.productservice.vo.response.status;

public enum ResponseStatus {

    SU("Success"),
    FA("Fail"),
    NS("NotEnoughStock");

    String message;

    ResponseStatus(String message){
        this.message = message;
    }

}
