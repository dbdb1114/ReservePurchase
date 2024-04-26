package com.reservation.productservice.vo.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ResponseProduct {

    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

}
