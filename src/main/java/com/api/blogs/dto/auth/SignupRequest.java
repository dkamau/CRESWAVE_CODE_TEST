package com.api.blogs.dto.auth;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String fullName;
    private String email;
    private String password;
    private List<String> roles;
}
