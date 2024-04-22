package reservpurchase.service.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import reservpurchase.service.dto.ProductDto;
import reservpurchase.service.dto.WishListDto;
import org.springframework.http.ResponseEntity;
import reservpurchase.service.service.MemberService;
import reservpurchase.service.service.WishListService;
import reservpurchase.service.vo.request.RequestWishList;
import reservpurchase.service.util.encrypt.EncryptManager;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/wish-service")
public class WishListController {

    MemberService memberService;
    WishListService wishListService;
    @PutMapping("/add")
    public ResponseEntity addWishList(@RequestBody RequestWishList request){

        Long memberId = memberService.findIdByEmail(EncryptManager.infoEncode(request.getEmail()));
        WishListDto wishListDto = WishListDto.builder()
                .memberId(memberId)
                .product(ProductDto.builder().id(request.getProductId()).build())
                .count(request.getCount())
                .build();
        WishListDto savedDto = wishListService.addProduct(wishListDto);

        if(savedDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(savedDto.getCount());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
        }
    }
}
