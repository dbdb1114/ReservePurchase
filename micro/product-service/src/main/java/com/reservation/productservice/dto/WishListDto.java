package com.reservation.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private Long id;
    private Long memberId;
    private ProductDto product;
    private Integer quantity;

    public Integer getQuantity(){
        return this.quantity;
    }

    public Long getId() {
        return this.id;
    }

}
