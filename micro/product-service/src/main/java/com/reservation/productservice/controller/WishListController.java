package com.reservation.productservice.controller;

import com.reservation.productservice.dto.WishListDto;
import com.reservation.productservice.entity.WishList;
import com.reservation.productservice.service.WishListService;
import com.reservation.productservice.vo.request.RequestWishList;
import com.reservation.productservice.vo.response.ResponseWishList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/wish-service")
public class WishListController {

    WishListService wishListService;
    ModelMapper modelMapper;

    @PutMapping("/add")
    public ResponseEntity addWishList(@RequestBody RequestWishList requestWishList){

        // memberID를 memberService 에서 가져와야함
//        Long memberId = memberService.findIdByEmail(EncryptManager.infoEncode(requestWishList.getEmail()));
//        WishListDto wishListDto = WishListDto.builder()
//                .memberId(memberId)
//                .product(ProductDto.builder().id(requestWishList.getProductId()).build())
//                .quantity(requestWishList.getQuantity())
//                .build();
//        WishListDto savedDto = wishListService.addProduct(wishListDto);
//
//        if(savedDto != null){
//            return ResponseEntity.status(HttpStatus.OK).body(savedDto);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
//        }
        return null;
    }

    @PostMapping("/wish/{memberId}")
    public ResponseEntity<List<ResponseWishList>> memberWish(@PathVariable("memberId") Long memberId){
        List<WishList> memberWish = wishListService.memberWish(memberId);
        List<ResponseWishList> response = memberWish.stream()
                .map(wish -> modelMapper.map(wish, ResponseWishList.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
