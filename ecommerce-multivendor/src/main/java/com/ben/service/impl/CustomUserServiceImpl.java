package com.ben.service.impl;

import com.ben.domain.USER_ROLE;
import com.ben.modal.Seller;
import com.ben.modal.User;
import com.ben.repository.SellerRepository;
import com.ben.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX="seller_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        if (username.startsWith(SELLER_PREFIX)){
            String actualUsername = username.substring(SELLER_PREFIX.length());

            Seller seller = sellerRepository.findByEmail(actualUsername);


            if(seller!=null){
                return buildeUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
        }
        else{
            User user = userRepository.findByEmail(username);
            if (user!=null){
                return buildeUserDetails(user.getEmail(), user.getPassword(), user.getRole());
            }
        }
        throw new UsernameNotFoundException("user or seller not found with email "+username);
    }

    private UserDetails buildeUserDetails(String email, String password, USER_ROLE role) {
        if(role==null)
            role = USER_ROLE.ROLE_CUSTOMER;
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(
                email, password, authorityList
        );
    }
}
