package reservpurchase.service.controller;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservpurchase.service.dto.Paging;
import reservpurchase.service.dto.ProductDto;
import reservpurchase.service.service.ProductService;
import reservpurchase.service.vo.response.ResponseProduct;

@RestController
@NoArgsConstructor
@RequestMapping("/product-service")
public class ProductController {

    ProductService productService;
    ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{categoryId}/list")
    public ResponseEntity productList(@PathVariable Integer categoryId, @RequestBody @Nullable Paging paging){
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

}
