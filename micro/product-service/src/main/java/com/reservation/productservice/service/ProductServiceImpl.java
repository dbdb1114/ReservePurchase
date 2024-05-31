package com.reservation.productservice.service;

import com.reservation.productservice.dto.Paging;
import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.entity.Product;
import com.reservation.productservice.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;
    ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @CacheEvict(key = "#categoryId", cacheManager = "cacheManager")
    public List<ProductDto> productList(int categoryId, Paging paging) {
        PageRequest pageRequest = paging.getPageRequest();
        Page<Product> products = productRepository.findAllByCategoryId(categoryId, pageRequest);
        List<ProductDto> dtoPage = products.stream()
                .map(entity -> modelMapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
        return dtoPage;
    }

    @Override
    @CacheEvict(key = "#productId", cacheManager = "cacheManager")
    public ProductDto productDetail(Long productId) {
        Product product = productRepository.findById(productId).get();
        ProductDto dto = modelMapper.map(product, ProductDto.class);
        return dto;
    }

    @Override
    public List<ProductDto> findProductInfoList(List<Long> productIdList) {
        List<Product> productList = productRepository.findAllByIdIn(productIdList);
        return productList.stream().map(ett->modelMapper.map(ett,ProductDto.class)).collect(Collectors.toList());
    }

}
