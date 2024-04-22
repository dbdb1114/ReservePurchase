package reservpurchase.service.service;

import org.springframework.data.domain.Page;
import reservpurchase.service.dto.Paging;
import reservpurchase.service.dto.ProductDto;

public interface ProductService {

     Page<ProductDto> productList(int categoryId, Paging paging);
     ProductDto productDetail(Long id);

}
