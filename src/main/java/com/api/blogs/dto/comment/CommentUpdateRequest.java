package com.api.blogs.dto.comment;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequest {
    private UUID commentId;
    private String comment;
}
