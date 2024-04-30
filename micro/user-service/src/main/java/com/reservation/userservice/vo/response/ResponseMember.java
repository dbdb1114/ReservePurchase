package com.reservation.userservice.vo.response;

import com.reservation.userservice.entity.Address;
import lombok.Getter;

@Getter
public class ResponseMember extends ResponseVo {
    private String email;
    private String name;
    private String phone;
    private Address address;
}
