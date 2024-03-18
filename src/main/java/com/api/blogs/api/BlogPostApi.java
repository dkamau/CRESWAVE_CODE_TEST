package com.api.blogs.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.api.blogs.dto.blogpost.BlogPostCreateRequest;
import com.api.blogs.dto.blogpost.BlogPostListResponse;
import com.api.blogs.dto.blogpost.BlogPostUpdateRequest;
import com.api.blogs.model.BlogPost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Blog Posts", description = "Blog Posts Api")
public interface BlogPostApi {

    @Operation(summary = "Get All", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<List<BlogPostListResponse>> getAll();

    @Operation(summary = "Paginate", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<Page<BlogPost>> getAll(Integer pageNumber, Integer pageSize,
            String sort);

    @Operation(summary = "Get By Id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<Optional<BlogPost>> getById(UUID id);

    @Operation(summary = "Create", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<BlogPost> add(BlogPostCreateRequest blogPostRequest);

    @Operation(summary = "Update", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<BlogPost> update(BlogPostUpdateRequest blogPostRequest);

    @Operation(summary = "Delete", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<Optional<BlogPost>> delete(UUID id);
}
