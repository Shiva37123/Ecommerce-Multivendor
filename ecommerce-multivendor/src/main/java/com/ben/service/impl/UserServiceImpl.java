package com.ben.service.impl;

import com.ben.config.JwtProvider;
import com.ben.modal.User;
import com.ben.repository.UserRepository;
import com.ben.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.findUserByEmail(email);

    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not fount with email - "+email);
        }
        return user;
    }
}
