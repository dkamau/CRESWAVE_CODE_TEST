package com.api.blogs.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.api.blogs.dto.comment.CommentCreateRequest;
import com.api.blogs.dto.comment.CommentListResponse;
import com.api.blogs.dto.comment.CommentUpdateRequest;
import com.api.blogs.model.Comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Comments", description = "Comments Api")
public interface CommentApi {

        @Operation(summary = "Get All", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<List<CommentListResponse>> getAll();

        @Operation(summary = "Paginate", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<Page<Comment>> getAll(Integer pageNumber, Integer pageSize,
                        String sort);

        @Operation(summary = "Get By Id", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<Optional<Comment>> getById(UUID id);

        @Operation(summary = "Create", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<Comment> add(CommentCreateRequest commentRequest);

        @Operation(summary = "Update", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<Comment> update(CommentUpdateRequest commentRequest);

        @Operation(summary = "Delete", description = "")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK")
        })
        ResponseEntity<Optional<Comment>> delete(UUID id);
}
