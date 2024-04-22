package reservpurchase.service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer stock;

}
