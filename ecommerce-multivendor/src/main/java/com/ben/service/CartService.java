package com.ben.service;


import com.ben.modal.Cart;
import com.ben.modal.CartItem;
import com.ben.modal.Product;
import com.ben.modal.User;

public interface CartService {

    public CartItem addCardItem(User user, Product product, String size, int quantity);
    public Cart findUserCart(User user);

}
