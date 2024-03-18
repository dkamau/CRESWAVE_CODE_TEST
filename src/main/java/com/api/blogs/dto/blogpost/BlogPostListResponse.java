package com.api.blogs.dto.blogpost;

import java.time.Instant;
import java.util.UUID;

import com.api.blogs.dto.auth.UserResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPostListResponse {
    private UUID id;
    private String title;
    private String content;
    private Instant dateCreated;
    private Instant datePublished;
    private Boolean isPublished;
    private UserResponse user;
}
