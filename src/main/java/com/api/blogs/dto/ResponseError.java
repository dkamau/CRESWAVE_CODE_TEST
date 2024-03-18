package com.api.blogs.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseError {
    private final String message;
}
