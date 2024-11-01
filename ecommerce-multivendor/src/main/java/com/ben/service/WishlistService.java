package com.ben.service;

import com.ben.modal.Product;
import com.ben.modal.User;
import com.ben.modal.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
