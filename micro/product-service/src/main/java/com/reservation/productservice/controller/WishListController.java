package com.reservation.productservice.controller;

import com.reservation.productservice.dto.ProductDto;
import com.reservation.productservice.dto.WishListDto;
import com.reservation.productservice.entity.WishList;
import com.reservation.productservice.feign.MemberClient;
import com.reservation.productservice.service.ProductService;
import com.reservation.productservice.service.WishListService;
import com.reservation.productservice.vo.request.RequestWishList;
import com.reservation.productservice.vo.response.status.ResponseStatus;
import com.reservation.productservice.vo.response.status.ResponseVo;
import com.reservation.productservice.vo.response.ResponseWishList;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/wish")
public class WishListController {

    WishListService wishListService;
    ProductService productService;
    MemberClient memberClient;
    ModelMapper modelMapper;

    @PostMapping("/list/{memberId}")
    public ResponseEntity<List<ResponseWishList>> memberWish(@PathVariable("memberId") Long memberId){
        List<WishList> memberWish = wishListService.memberWish(memberId);

        List<ResponseWishList> response = memberWish.stream()
                .map(wish -> modelMapper.map(wish, ResponseWishList.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/list")
    public ResponseEntity<ResponseVo> memberWish(HttpServletRequest request){
        Long memberId = memberClient.memberId(request.getHeader("Authorization"));
        List<WishList> memberWish = wishListService.memberWish(memberId);

        List<ResponseWishList> wishLists = memberWish.stream()
                .map(wish -> modelMapper.map(wish, ResponseWishList.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU,wishLists));
    }

    @PutMapping("/add")
    public ResponseEntity<ResponseVo> addWishList(@RequestBody RequestWishList requestWishList,
                                    HttpServletRequest request){

        Long memberId = memberClient.memberId(request.getHeader("Authorization"));

        WishListDto wishListDto = WishListDto.builder()
                .memberId(memberId)
                .product(ProductDto.builder().id(requestWishList.getProductId()).build())
                .quantity(requestWishList.getQuantity())
                .build();

        WishListDto savedDto = wishListService.addWish(wishListDto);

        if(savedDto != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo(ResponseStatus.SU));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(ResponseStatus.FA));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteWishList(@RequestBody RequestWishList requestWishList){
        Boolean result = wishListService.delete(requestWishList.getId());
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo<>(ResponseStatus.FA));
        }
    }

    @PutMapping("/change-quantity")
    public ResponseEntity changeQuantity(@RequestBody RequestWishList requestWishList){
        WishListDto dto = modelMapper.map(requestWishList, WishListDto.class);
        wishListService.changeQuantity(dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseVo<>(ResponseStatus.SU, dto));
    }
}
