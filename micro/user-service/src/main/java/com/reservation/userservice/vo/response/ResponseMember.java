package com.reservation.userservice.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.reservation.userservice.entity.Address;
import com.reservation.userservice.vo.response.auth.ResponseVo;
import lombok.Data;
@Data
@JsonInclude(Include.NON_NULL) // RestAPI로 데이터를 반환할 때 NULL은 제외한다 .
public class ResponseMember extends ResponseVo {
    private String email;
    private String name;
    private String password;
    private String phone;
    private Address address;
}
