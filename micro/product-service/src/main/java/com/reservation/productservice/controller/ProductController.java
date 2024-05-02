package com.reservation.productservice.controller;

import com.reservation.productservice.dto.Paging;
import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.service.ProductService;
import com.reservation.productservice.vo.request.RequestOrderItem;
import com.reservation.productservice.vo.response.ResponseProduct;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class ProductController {

    ProductService productService;
    ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{categoryId}/list")
    public ResponseEntity productList(@PathVariable Integer categoryId, @RequestBody @Nullable Paging paging) {
        Page<ProductDto> productDtos = productService.productList(categoryId, paging);

        if(productDtos.getContent().size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(productDtos);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}");
        }
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<ResponseProduct> productDetail(@PathVariable("productId") Long productId){
        ProductDto productDto = productService.productDetail(productId);
        if(productDto != null){
            ResponseProduct responseProduct = modelMapper.map(productDto, ResponseProduct.class);
            return ResponseEntity.status(HttpStatus.OK).body(responseProduct);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/product/list-info")
    public Map<Long, ProductDto> productInfo(@RequestBody ArrayList<Long> productIdList){
        List<ProductDto> productInfoList = productService.findProductInfoList(productIdList);
        Map<Long, ProductDto> productDtoMap = new HashMap<>();

        productInfoList.stream().forEach(dto->productDtoMap.put(dto.getId(), dto));
        return productDtoMap;
    }

    @PostMapping("/product/stock-decrease")
    public void completeOrder(@RequestBody ArrayList<RequestOrderItem> orderItems){
        productService.stockDecrease(orderItems);
    }

}
