package com.api.blogs.dto.comment;

import java.time.Instant;
import java.util.UUID;

import com.api.blogs.dto.auth.UserResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private UUID id;
    private String comment;
    private Instant dateCreated;
    private UserResponse user;
}
