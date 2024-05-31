package com.reservation.orderservice.feign;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.reservation.orderservice.vo.HttpOrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "STOCK-SERVICE", url = "localhost:8000/api/v1/stock-service/stock")
public interface StockClient {
    @PutMapping("/update/decrease")
    ResponseEntity decreaseStock(@RequestHeader(value = "Authorization") String authorization, @RequestBody List<? extends HttpOrderItem> orderItemList);
    @PutMapping("/update/increase")
    ResponseEntity increaseStock(@RequestHeader(value = "Authorization") String authorization, @RequestBody List<? extends HttpOrderItem> orderItemList);
}
