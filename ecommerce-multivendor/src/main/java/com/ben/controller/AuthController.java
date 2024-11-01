package com.ben.controller;

import com.ben.domain.USER_ROLE;
import com.ben.modal.User;
import com.ben.modal.VerificationCode;
import com.ben.repository.UserRepository;
import com.ben.request.LoginOtpRequest;
import com.ben.request.LoginRequest;
import com.ben.response.ApiResponse;
import com.ben.response.AuthResponse;
import com.ben.response.SignupRequest;
import com.ben.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register Success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);

        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/longin-signup-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {

        authService.sentLoginOtp(req.getEmail(), req.getRole());

        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        AuthResponse authResponse = authService.signing(req);


        return ResponseEntity.ok(authResponse);
    }
}
