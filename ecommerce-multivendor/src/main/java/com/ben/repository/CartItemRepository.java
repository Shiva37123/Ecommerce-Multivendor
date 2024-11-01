package com.ben.repository;

import com.ben.modal.Cart;
import com.ben.modal.CartItem;
import com.ben.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
