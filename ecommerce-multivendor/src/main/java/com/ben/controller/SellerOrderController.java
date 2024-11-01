package com.ben.controller;

import com.ben.domain.OrderStatus;
import com.ben.exceptions.SellerException;
import com.ben.modal.Order;
import com.ben.modal.Seller;
import com.ben.modal.User;
import com.ben.service.OrderService;
import com.ben.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/orders")
public class SellerOrderController {

    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrdersHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        Seller seller = sellerService.getSellerProfile(jwt);
        List<Order> orders=orderService.sellersOrder(seller.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(@PathVariable Long orderId,
                                                    @PathVariable OrderStatus orderStatus,
                                             @RequestHeader("Authorization") String jwt) throws Exception {

//        User user = userService.findUserByJwtToken(jwt);

        Order order = orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

}
