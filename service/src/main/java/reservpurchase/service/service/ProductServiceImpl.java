package reservpurchase.service.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.ProductDto;
import reservpurchase.service.entity.ProductEntity;
import reservpurchase.service.repository.ProductRepository;

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
    public ProductDto productDetail(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        ProductDto dto = modelMapper.map(productEntity, ProductDto.class);
        return dto;
    }


}
