package com.api.blogs.dto.comment;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private UUID userId;
    private UUID blogPostId;
    private String comment;
}
