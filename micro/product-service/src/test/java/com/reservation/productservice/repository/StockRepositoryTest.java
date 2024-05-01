package com.reservation.productservice.repository;

import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.dto.StockDto;
import com.reservation.productservice.entity.Product;
import com.reservation.productservice.entity.Stock;
import com.reservation.productservice.vo.request.RequestOrderItem;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StockRepositoryTest {

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    @Test
    void 재고조회() {
        //given
        Stock stock = Stock.builder()
                .inventory(100)
                .build();
        Product 테스트용_상품 = Product.builder()
                .name("책100: 신경 끄기의 기술")
                .categoryId(3)
                .price(1000)
                .stock(stock)
                .build();

        stock.setProduct(테스트용_상품);
        Product 저장테스트상품 = productRepository.save(테스트용_상품);

        //when
        ProductDto map = modelMapper.map(저장테스트상품, ProductDto.class);
        Stock 저장Stock = stockRepository.findByProductId(map.getId());
        StockDto dto = modelMapper.map(저장Stock, StockDto.class);

        //then
        Assertions.assertThat(dto.getInventory()).isEqualTo(100);
    }

    @Test
    @DisplayName("상품 홑건 재고 수정")
    void 홑건재고감소(){
        //given
        Stock stock = Stock.builder()
                .inventory(100)
                .build();
        Product 테스트용_상품 = Product.builder()
                .categoryId(3)
                .name("테스트용 상품")
                .price(1000)
                .stock(stock)
                .build();

        stock.setProduct(테스트용_상품);
        Product 저장테스트상품 = productRepository.save(테스트용_상품);
        ProductDto map = modelMapper.map(저장테스트상품, ProductDto.class);

        //when
        // 저장한 상품에 대한 orderRequest를 만들고
        // 해당 상품에 대한 stock을 업데이트 해줌.
        RequestOrderItem requestOrderItem1 = new RequestOrderItem();
        requestOrderItem1.setProductId(map.getId());
        requestOrderItem1.setQuantity(3);

        Stock stock1 = stockRepository.findByProductId(requestOrderItem1.getProductId());
        stock1.decreaseInventory(requestOrderItem1.getQuantity());

        //then
        Stock updatedStock = stockRepository.findByProductId(map.getId());
        StockDto map1 = modelMapper.map(updatedStock, StockDto.class);
        Assertions.assertThat(map1.getInventory()).isEqualTo(97);
    }

    @Test
    @DisplayName("상품 다건 재고 수정")
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

        ProductDto map1 = modelMapper.map(저장테스트상품1, ProductDto.class);
        ProductDto map2 = modelMapper.map(저장테스트상품2, ProductDto.class);
        ProductDto map3 = modelMapper.map(저장테스트상품3, ProductDto.class);

        //when
        // 저장한 상품에 대한 orderRequest를 만들고
        // 해당 상품에 대한 stock을 업데이트 해줌.
        List<RequestOrderItem> rqoItem = new ArrayList<>();

        RequestOrderItem requestOrderItem1 = new RequestOrderItem();
        requestOrderItem1.setProductId(map1.getId());
        requestOrderItem1.setQuantity(1);
        rqoItem.add(requestOrderItem1);

        RequestOrderItem requestOrderItem2 = new RequestOrderItem();
        requestOrderItem2.setProductId(map2.getId());
        requestOrderItem2.setQuantity(2);
        rqoItem.add(requestOrderItem2);

        RequestOrderItem requestOrderItem3 = new RequestOrderItem();
        requestOrderItem3.setProductId(map3.getId());
        requestOrderItem3.setQuantity(3);
        rqoItem.add(requestOrderItem3);

        rqoItem.stream().forEach(item -> {
            Stock savedStock = stockRepository.findByProductId(item.getProductId());
            savedStock.decreaseInventory(item.getQuantity());
        });

        //then
        Stock updatedStock1 = stockRepository.findByProductId(map1.getId());
        Stock updatedStock2 = stockRepository.findByProductId(map2.getId());
        Stock updatedStock3 = stockRepository.findByProductId(map3.getId());

        StockDto stockDto1 = modelMapper.map(updatedStock1, StockDto.class);
        StockDto stockDto2 = modelMapper.map(updatedStock2, StockDto.class);
        StockDto stockDto3 = modelMapper.map(updatedStock3, StockDto.class);

        Assertions.assertThat(stockDto1.getInventory()).isEqualTo(110);
        Assertions.assertThat(stockDto2.getInventory()).isEqualTo(220);
        Assertions.assertThat(stockDto3.getInventory()).isEqualTo(330);
    }
}
