package com.api.blogs.dto.blogpost;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostCreateRequest {
    private UUID userId;
    private String title;
    private String content;
    private Boolean isPublished;
}
