package com.api.blogs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blogs.api.AuthApi;
import com.api.blogs.dto.ResponseError;
import com.api.blogs.dto.auth.LoginRequest;
import com.api.blogs.dto.auth.SignupRequest;
import com.api.blogs.dto.auth.SignupResponse;
import com.api.blogs.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest) {
        var response = authService.login(loginRequest);

        if (response == null || response.getAccessToken().isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseError.builder().message("Invalid Credentials, please try again!").build());

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Validated SignupRequest signupRequest) {
        try {
            var user = authService.signup(signupRequest);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            switch (ex.getMessage()) {
                case "UserExists":
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResponseError.builder().message("A user with the provided email already exists.")
                                    .build());
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResponseError.builder().message("An unexpected error occured").build());
            }
        }
    }

    @Override
    @GetMapping("/users")
    public ResponseEntity<List<SignupResponse>> list() {
        return ResponseEntity.ok(authService.listUsers());
    }
}
