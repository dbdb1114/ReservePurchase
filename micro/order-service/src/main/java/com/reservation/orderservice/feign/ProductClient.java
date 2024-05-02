package com.reservation.orderservice.feign;

import java.util.List;
import com.reservation.orderservice.dto.ProductDto;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCT-SERVICE", url = "localhost:8000/api/v1/product-service/")
public interface ProductClient {
    @PostMapping(value = "/product/list-info")
    Map<Long,ProductDto> productInfo(@RequestBody List<Long> productIdList);
}
