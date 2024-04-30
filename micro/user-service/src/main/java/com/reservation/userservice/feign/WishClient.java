package com.reservation.userservice.feign;

import com.reservation.userservice.vo.response.ResponseWishList;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PRODUCT-SERVICE", url = "localhost:8000/api/v1/product-service/wish/")
public interface WishClient {

    @PostMapping(value = "list/{memberId}")
    List<ResponseWishList> memberWish(@PathVariable Long memberId);

}
