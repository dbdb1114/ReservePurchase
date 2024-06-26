package reservpurchase.service.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reservpurchase.service.dto.Paging;
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
    public Page<ProductDto> productList(int categoryId, Paging paging) {
        PageRequest pageRequest = paging.getPageRequest();
        Page<ProductEntity> allByCategoryId = productRepository.findAllByCategoryId(categoryId, pageRequest);
        Page<ProductDto> dtoPage = allByCategoryId.map(x -> modelMapper.map(x, ProductDto.class));
        return dtoPage;
    }

    @Override
    public ProductDto productDetail(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        ProductDto dto = modelMapper.map(productEntity, ProductDto.class);
        return dto;
    }


}
