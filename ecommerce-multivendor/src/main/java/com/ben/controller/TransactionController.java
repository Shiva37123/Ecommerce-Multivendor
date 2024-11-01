package com.ben.controller;

import com.ben.modal.Order;
import com.ben.modal.Seller;
import com.ben.modal.Transaction;
import com.ben.modal.User;
import com.ben.service.SellerService;
import com.ben.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final SellerService sellerService;

    @GetMapping("/user")
    public ResponseEntity<List<Transaction>> usersOrderHistoryHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        Seller seller = sellerService.getSellerProfile(jwt);
        List<Transaction> transactions = transactionService.getTransactionBySellerId(seller);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> transactions = transactionService.getAllTransaction();
        return ResponseEntity.ok(transactions);
    }
}
