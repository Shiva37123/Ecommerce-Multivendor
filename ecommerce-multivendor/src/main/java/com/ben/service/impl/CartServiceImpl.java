package com.ben.service.impl;

import com.ben.config.JwtProvider;
import com.ben.domain.USER_ROLE;
import com.ben.modal.*;
import com.ben.repository.*;
import com.ben.request.LoginRequest;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;
import com.ben.service.AuthService;
import com.ben.service.CartService;
import com.ben.service.EmailService;
import com.ben.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final JwtProvider jwtProvider;


    @Override
    public CartItem addCardItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);

        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);
            int totalPrice = quantity*product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);

        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice=0;
        int totalDiscountPrice=0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getMrpPrice();
            totalDiscountPrice += cartItem.getSellingPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalMrpPrice(totalDiscountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountPrice));
        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
//            throw new IllegalArgumentException("actual price must be greater than 0");
            return 0;
        }
        double discount = mrpPrice-sellingPrice;
        double discountPercentage = (discount/mrpPrice)*100;

        return (int)discountPercentage;
    }

}
