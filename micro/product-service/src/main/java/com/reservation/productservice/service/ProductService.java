package com.reservation.productservice.service;

import com.reservation.productservice.dto.Paging;
import com.reservation.productservice.dto.ProductDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

     Page<ProductDto> productList(int categoryId, Paging paging);
     ProductDto productDetail(Long id);
     Boolean isEnoughStock(Long id);

     List<ProductDto> findProductInfoList(List<Long> productIdList);
}
