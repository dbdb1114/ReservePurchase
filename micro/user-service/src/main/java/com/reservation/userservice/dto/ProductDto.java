package com.reservation.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

}
