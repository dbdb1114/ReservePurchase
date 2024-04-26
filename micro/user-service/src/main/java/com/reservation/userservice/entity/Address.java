package com.reservation.userservice.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {

    private String city;
    private String detailAddress;

}
