package com.api.blogs.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.blogs.dto.blogpost.BlogPostCreateRequest;
import com.api.blogs.dto.blogpost.BlogPostListResponse;
import com.api.blogs.dto.blogpost.BlogPostUpdateRequest;
import com.api.blogs.model.BlogPost;
import com.api.blogs.repository.BlogPostRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public List<BlogPostListResponse> getAll() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        List<BlogPostListResponse> blogPostListResponses = new ArrayList<BlogPostListResponse>();

        for (BlogPost blogPost : blogPosts) {
            BlogPostListResponse blogPostListResponse = BlogPostListResponse.builder()
                    .title(blogPost.getTitle())
                    .content(blogPost.getContent())
                    .dateCreated(blogPost.getDateCreated())
                    .datePublished(blogPost.getDatePublished())
                    .isPublished(blogPost.getIsPublished()).build();

            blogPostListResponses.add(blogPostListResponse);
        }

        return blogPostListResponses;
    }

    public Page<BlogPost> getAll(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> getById(UUID id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost add(BlogPostCreateRequest blogPostRequest) {
        BlogPost blogPost = new BlogPost(null, blogPostRequest.getTitle(), blogPostRequest.getContent(), Instant.now(),
                null, blogPostRequest.getIsPublished(), null, null);
        blogPost.getUser().setId(blogPostRequest.getUserId());
        blogPost.setDatePublished(blogPostRequest.getIsPublished() ? Instant.now() : null);

        return blogPostRepository.saveAndFlush(blogPost);
    }

    public BlogPost update(BlogPostUpdateRequest blogPostRequest) {
        Optional<BlogPost> blogPost = getById(blogPostRequest.getId());
        if (blogPost != null) {
            BlogPost updateBlogPost = blogPost.get();
            updateBlogPost.setTitle(blogPostRequest.getTitle());
            updateBlogPost.setContent(blogPostRequest.getContent());
            updateBlogPost.setIsPublished(blogPostRequest.getIsPublished());
            updateBlogPost.setDatePublished(blogPostRequest.getIsPublished() ? Instant.now() : null);

            return blogPostRepository.saveAndFlush(updateBlogPost);
        }

        return null;
    }

    public Optional<BlogPost> delete(UUID id) {
        var blogPost = getById(id);
        blogPostRepository.deleteById(id);
        return blogPost;
    }
}
