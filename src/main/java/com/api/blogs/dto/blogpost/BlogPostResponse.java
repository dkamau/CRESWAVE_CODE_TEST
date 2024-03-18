package com.api.blogs.dto.blogpost;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.api.blogs.dto.auth.UserResponse;
import com.api.blogs.dto.comment.CommentResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostResponse {
    private UUID id;
    private String title;
    private String content;
    private Instant dateCreated = Instant.now();
    private Instant datePublished = Instant.now();
    private Boolean isPublished = true;
    private UserResponse user;
    private List<CommentResponse> comments;
}
