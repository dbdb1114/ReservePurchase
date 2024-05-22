package com.reservation.userservice.vo.response.status;

import com.reservation.userservice.vo.response.status.ResponseVo;

public enum EmailAuthStatus {

    SU("Success"),
    FA("Fail");

    String message;

    EmailAuthStatus(String message){
        this.message = message;
    }

}
