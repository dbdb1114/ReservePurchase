package com.reservation.userservice.vo.response.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo {

    public ResponseVo(ResponseStatus responseStatus) {
        this.responseCode = responseStatus.name();
        this.responseMessage = responseStatus.message;
    }

    public ResponseVo(EmailAuthStatus emailAuthStatus) {
        this.responseCode = emailAuthStatus.name();
        this.responseMessage = emailAuthStatus.message;
    }

    public ResponseVo(JoinStatus joinStatus) {
        this.responseCode = joinStatus.name();
        this.responseMessage = joinStatus.message;
    }

    String responseCode;
    String responseMessage;

}
