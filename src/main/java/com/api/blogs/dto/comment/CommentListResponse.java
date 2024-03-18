package com.api.blogs.dto.comment;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentListResponse {
    private UUID id;
    private String comment;
    private Instant dateCreated;
}
