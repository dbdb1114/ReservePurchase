package com.reservation.userservice.vo.response.auth;

import com.reservation.userservice.vo.response.ResponseVo;

public enum EmailAuthStatus {

    SU("Success"),
    FA("Fail");

    EmailAuthStatus(String message){
        this.responseVo.setResponseCode(this.name());
        this.responseVo.setResponseMessage(message);
    }

    public ResponseVo responseVo = new ResponseVo();

}
