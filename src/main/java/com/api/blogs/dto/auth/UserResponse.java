package com.api.blogs.dto.auth;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String fullName;
    private String email;
}
