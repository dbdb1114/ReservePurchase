package com.reservation.userservice.vo.response.status;

import com.reservation.userservice.vo.response.status.ResponseVo;

public enum JoinStatus{
    SU("Success"),
    DE("Duplicate Email"),
    DP("Duplicate Phone"),
    FA("Fail");

    String message;

    JoinStatus(String message){
        this.message = message;
    }

}
