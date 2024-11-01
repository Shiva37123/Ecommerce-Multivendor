package com.ben.service;

import com.ben.domain.USER_ROLE;
import com.ben.request.LoginRequest;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignupRequest request) throws Exception;

    AuthResponse signing(LoginRequest req) throws Exception;

}
