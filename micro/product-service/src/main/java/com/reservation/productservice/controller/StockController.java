package com.reservation.productservice.controller;

import com.reservation.productservice.service.StockService;
import com.reservation.productservice.vo.request.RequestOrderItem;
import com.reservation.productservice.vo.response.ResponseStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @PutMapping("/stock/update/decrease")
    public ResponseEntity decreaseStock(@RequestBody List<RequestOrderItem> orderItemList){
        Integer resCount = stockService.decreaseStock(orderItemList);

        if(resCount == orderItemList.size()){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseStatus.SU.responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ResponseStatus.FA.responseVo);
        }
    }

    @PutMapping("/stock/update/increase")
    public ResponseEntity updateStock(@RequestBody List<RequestOrderItem> orderItemList){
        Integer resCount = stockService.increaseStock(orderItemList);

        if(resCount == orderItemList.size()){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseStatus.SU.responseVo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ResponseStatus.FA.responseVo);
        }

    }

}
