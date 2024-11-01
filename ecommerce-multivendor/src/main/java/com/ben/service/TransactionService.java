package com.ben.service;

import com.ben.modal.Order;
import com.ben.modal.Seller;
import com.ben.modal.Transaction;

import java.util.List;

public interface TransactionService {


    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransaction();
}
