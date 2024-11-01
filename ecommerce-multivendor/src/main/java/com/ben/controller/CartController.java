package com.ben.controller;

import com.ben.exceptions.ProductException;
import com.ben.exceptions.SellerException;
import com.ben.modal.*;
import com.ben.request.AddItemRequest;
import com.ben.request.CreateProductRequest;
import com.ben.response.ApiResponse;
import com.ben.service.CartItemService;
import com.ben.service.CartService;
import com.ben.service.ProductService;
import com.ben.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization") String jwt) throws ProductException, Exception{

        User user = userService.findUserByJwtToken(jwt);

        Product product = productService.findProductById(req.getProductId());
        CartItem item = cartService.addCardItem(user,product,req.getSize(),req.getQuantity());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Item added to cart successfully");
        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);

        cartItemService.removeCartItem(user.getId(), cartItemId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Item Removed From Cart");
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);

    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler(@PathVariable Long cartItemId,
                                                 @RequestBody CartItem cartItem,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if(cartItem.getQuantity()>0){
            updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        }

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);

    }

}
