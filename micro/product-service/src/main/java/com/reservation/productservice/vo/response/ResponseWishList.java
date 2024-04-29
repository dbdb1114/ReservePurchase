package com.reservation.productservice.vo.response;

import com.reservation.productservice.dto.ProductDto;

public class ResponseWishList extends ResponseVo{
    private Long id;
    private Long memberId;
    private ProductDto product;
    private Integer quantity;

    @Override
    public String toString() {
        return "ResponseWishList{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
