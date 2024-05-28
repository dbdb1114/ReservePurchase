package com.reservation.productservice.controller;

import com.reservation.productservice.dto.Paging;
import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.service.ProductService;
import com.reservation.productservice.vo.response.ResponseProduct;
import com.reservation.productservice.vo.response.status.ResponseStatus;
import com.reservation.productservice.vo.response.status.ResponseVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{categoryId}/list/{page}/{limit}/{orderBy}/{offset}")
    public ResponseEntity<ResponseVo> productList(@PathVariable Integer categoryId, @PathVariable Integer page, @PathVariable Integer limit,
                                                  @PathVariable String orderBy, @PathVariable String offset) {

        Paging paging = new Paging();
        paging.setPage(page);
        paging.setLimit(limit);
        paging.setOrderBy(orderBy);
        paging.setDirection(offset);

        List<ResponseProduct> productDtos = productService.productList(categoryId, paging)
                .stream().map(dto->modelMapper.map(dto,ResponseProduct.class)).toList();

        if(!productDtos.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU, productDtos));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<ResponseVo> productDetail(@PathVariable("productId") Long productId){
        ProductDto productDto = productService.productDetail(productId);

        if(productDto != null){
            ResponseProduct responseProduct = modelMapper.map(productDto, ResponseProduct.class);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU,responseProduct));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    /**
     *  주문서 생성 용도 정보 리스트
     */
    @PostMapping("/product/list-info")
    public Map<Long, ProductDto> productInfo(@RequestBody ArrayList<Long> productIdList){
        List<ProductDto> productInfoList = productService.findProductInfoList(productIdList);
        Map<Long, ProductDto> productDtoMap = new HashMap<>();

        productInfoList.stream().forEach(dto->productDtoMap.put(dto.getId(), dto));
        return productDtoMap;
    }

}
