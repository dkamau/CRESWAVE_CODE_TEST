package com.api.blogs.dto.blogpost;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostUpdateRequest {
    private UUID id;
    private String title;
    private String content;
    private Boolean isPublished;
}
