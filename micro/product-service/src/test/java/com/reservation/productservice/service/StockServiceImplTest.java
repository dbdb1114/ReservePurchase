package com.reservation.productservice.service;

import static org.assertj.core.api.Assertions.*;

import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.dto.StockDto;
import com.reservation.productservice.entity.Product;
import com.reservation.productservice.entity.Stock;
import com.reservation.productservice.repository.ProductRepository;
import com.reservation.productservice.repository.StockRepository;
import com.reservation.productservice.vo.request.RequestOrderItem;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceImplTest {

    @Autowired
    StockService stockService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ModelMapper modelMapper;

    @Test
    @DisplayName("[SERVICE] 다건 재고 감소")
    void 다건재고감소(){
        //given
        Stock stock1 = Stock.builder()
                .inventory(111)
                .build();
        Product 테스트용_상품1 = Product.builder()
                .categoryId(3)
                .name("테스트용 상품")
                .price(1000)
                .stock(stock1)
                .build();

        Stock stock2 = Stock.builder()
                .inventory(222)
                .build();
        Product 테스트용_상품2 = Product.builder()
                .categoryId(3)
                .name("테스트용 상품")
                .price(1000)
                .stock(stock2)
                .build();

        Stock stock3 = Stock.builder()
                .inventory(333)
                .build();
        Product 테스트용_상품3 = Product.builder()
                .categoryId(3)
                .name("테스트용 상품")
                .price(1000)
                .stock(stock3)
                .build();

        stock1.setProduct(테스트용_상품1);
        stock2.setProduct(테스트용_상품2);
        stock3.setProduct(테스트용_상품3);

        Product 저장테스트상품1 = productRepository.save(테스트용_상품1);
        Product 저장테스트상품2 = productRepository.save(테스트용_상품2);
        Product 저장테스트상품3 = productRepository.save(테스트용_상품3);

        ProductDto saveTestDto1 = modelMapper.map(저장테스트상품1, ProductDto.class);
        ProductDto saveTestDto2 = modelMapper.map(저장테스트상품2, ProductDto.class);
        ProductDto saveTestDto3 = modelMapper.map(저장테스트상품3, ProductDto.class);

        //when
        // 저장한 상품에 대한 orderRequest를 만들고
        // 해당 상품에 대한 stock을 업데이트 해줌.
        List<RequestOrderItem> rqoItem = new ArrayList<>();

        RequestOrderItem requestOrderItem1 = new RequestOrderItem();
        requestOrderItem1.setProductId(saveTestDto1.getId());
        requestOrderItem1.setQuantity(1);
        rqoItem.add(requestOrderItem1);

        RequestOrderItem requestOrderItem2 = new RequestOrderItem();
        requestOrderItem2.setProductId(saveTestDto2.getId());
        requestOrderItem2.setQuantity(2);
        rqoItem.add(requestOrderItem2);

        RequestOrderItem requestOrderItem3 = new RequestOrderItem();
        requestOrderItem3.setProductId(saveTestDto3.getId());
        requestOrderItem3.setQuantity(3);
        rqoItem.add(requestOrderItem3);

        Integer resCount = stockService.decreaseStock(rqoItem);
        assertThat(resCount).isEqualTo(3).as("전체 테스트 결과");

        //then
        StockDto resStock1 = modelMapper.map(stockRepository.findByProductId(saveTestDto1.getId()), StockDto.class);
        StockDto resStock2 = modelMapper.map(stockRepository.findByProductId(saveTestDto2.getId()), StockDto.class);
        StockDto resStock3 = modelMapper.map(stockRepository.findByProductId(saveTestDto3.getId()), StockDto.class);


        assertThat(resStock1.getInventory()).isEqualTo(110).as("1번 상품");
        assertThat(resStock2.getInventory()).isEqualTo(220).as("2번 상품");;
        assertThat(resStock3.getInventory()).isEqualTo(330).as("3번 상품");;
    }

}
