package com.reservation.productservice.service;

import com.reservation.productservice.dto.Paging;
import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.entity.Product;
import com.reservation.productservice.repository.ProductRepository;
import com.reservation.productservice.vo.request.RequestOrderItem;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Page<ProductDto> productList(int categoryId, Paging paging) {
        PageRequest pageRequest = paging.getPageRequest();
        Page<Product> products = productRepository.findAllByCategoryId(categoryId, pageRequest);
        Page<ProductDto> dtoPage = products.map(entity -> modelMapper.map(entity, ProductDto.class));
        return dtoPage;
    }

    @Override
    public ProductDto productDetail(Long id) {
        Product product = productRepository.findById(id).get();
        ProductDto dto = modelMapper.map(product, ProductDto.class);
        return dto;
    }

    @Override
    public List<ProductDto> findProductInfoList(List<Long> productIdList) {
        List<Product> productList = productRepository.findAllByIdIn(productIdList);
        return productList.stream().map(ett->modelMapper.map(ett,ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public Boolean isEnoughStock(Long id) {
        return productRepository.findStockById(id) > 0;
    }

    @Override
    public void stockDecrease(List<RequestOrderItem> orderItemList) {
        orderItemList.stream().forEach(item->{
            Product product = productRepository.findById(item.getId()).get();
            product.decreaseStock(item.getQuantity());
        });
    }
}
