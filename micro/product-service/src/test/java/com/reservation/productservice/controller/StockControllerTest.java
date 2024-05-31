package com.reservation.productservice.controller;

import com.reservation.productservice.vo.request.RequestOrderItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockControllerTest {

    @Autowired
    StockController controller;

    @Test
    void 부하(){
        IntStream.range(1,1000).parallel().forEach(x->{
            List<RequestOrderItem> orderItemList = new ArrayList<>();
            RequestOrderItem requestOrderItem = new RequestOrderItem();
            requestOrderItem.setProductId(1L);
            requestOrderItem.setQuantity(1);
            orderItemList.add(requestOrderItem);
            controller.decreaseStock(orderItemList);
        });
    }
}
