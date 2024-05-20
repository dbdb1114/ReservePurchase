package com.reservation.productservice.controller;

import com.reservation.productservice.dto.StockDto;
import com.reservation.productservice.service.StockService;
import com.reservation.productservice.vo.request.RequestOrderItem;
import com.reservation.productservice.vo.response.ResponseStatus;
import com.reservation.productservice.vo.response.ResponseVo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/stock")
public class StockController {

    StockService stockService;

    @GetMapping("/{productId}")
    public ResponseEntity stockInfo(@PathVariable Long productId){
        StockDto stockDto = stockService.stockInfo(productId);
        if(stockDto!=null){
            ResponseVo<StockDto> responseVo = new ResponseVo<>(ResponseStatus.SU);
            responseVo.setData(stockDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseVo);
        } else if (stockDto.getInventory() <= 0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseVo<>(ResponseStatus.NS));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @PutMapping("/update/decrease")
    public ResponseEntity decreaseStock(@RequestBody List<RequestOrderItem> orderItemList){
        Integer resCount = stockService.decreaseStock(orderItemList);

        if(resCount == orderItemList.size()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @PutMapping("/update/increase")
    public ResponseEntity updateStock(@RequestBody List<RequestOrderItem> orderItemList){
        Integer resCount = stockService.increaseStock(orderItemList);

        if(resCount == orderItemList.size()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseVo<>(ResponseStatus.FA));
        }

    }

}
