package com.reservation.orderservice.vo.response;

import org.springframework.util.ObjectUtils;

public enum ResponseStatus {

    SU("Success"),
    FA("Fail"),
    NS("NotEnoughStock"),
    EC("EmptyContents");

    ResponseStatus(String message){
        this.responseVo.setResponseCode(this.name());
        this.responseVo.setResponseMessage(message);
    }

    private ResponseVo responseVo = new ResponseVo();

    public ResponseVo getResponseVo(){
        this.responseVo.setData("{}");
        return this.responseVo;
    }

}
