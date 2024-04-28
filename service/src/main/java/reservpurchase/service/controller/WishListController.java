package reservpurchase.service.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reservpurchase.service.dto.ProductDto;
import reservpurchase.service.dto.WishListDto;
import org.springframework.http.ResponseEntity;
import reservpurchase.service.entity.WishListEntity;
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
    ModelMapper modelMapper;

    @PutMapping("/add")
    public ResponseEntity addWishList(@RequestBody RequestWishList requestWishList){

        Long memberId = memberService.findIdByEmail(EncryptManager.infoEncode(requestWishList.getEmail()));
        WishListDto wishListDto = WishListDto.builder()
                .memberId(memberId)
                .product(ProductDto.builder().id(requestWishList.getProductId()).build())
                .quantity(requestWishList.getQuantity())
                .build();
        WishListDto savedDto = wishListService.addProduct(wishListDto);

        if(savedDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(savedDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
        }
    }

    @GetMapping("/select/{wishId}")
    public ResponseEntity<WishListDto> selectWish(@PathVariable("wishId") Long wishId){
        WishListEntity body = wishListService.selectWish(wishId);
        WishListDto map = modelMapper.map(body, WishListDto.class);
        System.out.println("body = " + body);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWishList(@RequestBody RequestWishList requestWishList){
        Boolean result = wishListService.delete(requestWishList.getId());
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body("SU");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FA");
        }
    }

    @PutMapping("/change-quantity")
    public ResponseEntity changeQuantity(@RequestBody RequestWishList requestWishList){
        WishListDto dto = modelMapper.map(requestWishList, WishListDto.class);
        wishListService.changeQuantity(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
