package com.ben.controller;

import com.ben.modal.User;
import com.ben.request.LoginRequest;
import com.ben.response.AuthResponse;
import com.ben.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> loginHandler(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);


        return ResponseEntity.ok(user);
    }

}
