package com.ben.service;

import com.ben.modal.User;
import com.ben.request.LoginRequest;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

}
