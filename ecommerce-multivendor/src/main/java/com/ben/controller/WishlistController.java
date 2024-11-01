package com.ben.controller;

import com.ben.domain.OrderStatus;
import com.ben.modal.*;
import com.ben.service.ProductService;
import com.ben.service.UserService;
import com.ben.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Wishlist> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);

        return new ResponseEntity<>(wishlist, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long productId,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {

        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwt);

        Wishlist updatedWishList = wishlistService.addProductToWishlist(user,product);
        return new ResponseEntity<>(updatedWishList, HttpStatus.ACCEPTED);
    }


}
