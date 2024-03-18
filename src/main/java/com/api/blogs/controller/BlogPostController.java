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

import com.api.blogs.api.BlogPostApi;
import com.api.blogs.dto.blogpost.BlogPostCreateRequest;
import com.api.blogs.dto.blogpost.BlogPostListResponse;
import com.api.blogs.dto.blogpost.BlogPostUpdateRequest;
import com.api.blogs.model.BlogPost;
import com.api.blogs.service.BlogPostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/blogposts")
public class BlogPostController implements BlogPostApi {

    private final BlogPostService blogPostService;

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BlogPostListResponse>> getAll() {
        return ResponseEntity.ok(blogPostService.getAll());
    }

    @Override
    @GetMapping(path = "/paginate", produces = "application/json")
    public ResponseEntity<Page<BlogPost>> getAll(Integer pageNumber, Integer pageSize, String sort) {
        return ResponseEntity.ok(blogPostService.getAll(pageNumber, pageSize, sort));
    }

    @GetMapping(path = "/id", produces = "application/json")
    public ResponseEntity<Optional<BlogPost>> getById(UUID id) {
        return ResponseEntity.ok(blogPostService.getById(id));
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<BlogPost> add(BlogPostCreateRequest blogPostRequest) {
        return ResponseEntity.ok(blogPostService.add(blogPostRequest));
    }

    @Override
    @PutMapping(produces = "application/json")
    public ResponseEntity<BlogPost> update(BlogPostUpdateRequest blogPostRequest) {
        return ResponseEntity.ok(blogPostService.update(blogPostRequest));
    }

    @Override
    @DeleteMapping(produces = "application/json")
    public ResponseEntity<Optional<BlogPost>> delete(UUID id) {
        return ResponseEntity.ok(blogPostService.delete(id));
    }
}
