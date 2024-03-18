package com.api.blogs.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blogs.api.CommentApi;
import com.api.blogs.dto.comment.CommentCreateRequest;
import com.api.blogs.dto.comment.CommentListResponse;
import com.api.blogs.dto.comment.CommentUpdateRequest;
import com.api.blogs.model.Comment;
import com.api.blogs.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController implements CommentApi {

    private final CommentService commentService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CommentListResponse>> getAll() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @Override
    @GetMapping(path = "/paginate", produces = "application/json")
    public ResponseEntity<Page<Comment>> getAll(Integer pageNumber, Integer pageSize, String sort) {
        return ResponseEntity.ok(commentService.getAll(pageNumber, pageSize, sort));
    }

    @GetMapping(path = "/id", produces = "application/json")
    public ResponseEntity<Optional<Comment>> getById(UUID id) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<Comment> add(CommentCreateRequest commentRequest) {
        return ResponseEntity.ok(commentService.add(commentRequest));
    }

    @Override
    @PutMapping(produces = "application/json")
    public ResponseEntity<Comment> update(CommentUpdateRequest commentRequest) {
        return ResponseEntity.ok(commentService.update(commentRequest));
    }

    @Override
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Optional<Comment>> delete(UUID id) {
        return ResponseEntity.ok(commentService.delete(id));
    }
}
