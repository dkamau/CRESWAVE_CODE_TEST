package com.api.blogs.dto.auth;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignupResponse {
    private UUID id;
    private String fullName;
    private String email;
    private List<String> roles;
}
