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

import com.api.blogs.dto.comment.CommentCreateRequest;
import com.api.blogs.dto.comment.CommentListResponse;
import com.api.blogs.dto.comment.CommentUpdateRequest;
import com.api.blogs.model.Comment;
import com.api.blogs.repository.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentListResponse> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentListResponse> commentListResponses = new ArrayList<CommentListResponse>();

        for (Comment comment : comments) {
            CommentListResponse commentListResponse = CommentListResponse.builder()
                    .comment(comment.getComment())
                    .dateCreated(comment.getDateCreated()).build();

            commentListResponses.add(commentListResponse);
        }

        return commentListResponses;
    }

    public Page<Comment> getAll(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        return commentRepository.findAll(pageable);
    }

    public Optional<Comment> getById(UUID id) {
        return commentRepository.findById(id);
    }

    public Comment add(CommentCreateRequest commentRequest) {
        Comment comment = new Comment(null, commentRequest.getComment(), Instant.now(),
                null, null);
        comment.getUser().setId(commentRequest.getUserId());
        comment.getBlogPost().setId(commentRequest.getBlogPostId());

        return commentRepository.saveAndFlush(comment);
    }

    public Comment update(CommentUpdateRequest commentRequest) {
        Optional<Comment> comment = getById(commentRequest.getCommentId());
        if (comment != null) {
            Comment updateComment = comment.get();
            updateComment.setComment(commentRequest.getComment());

            return commentRepository.saveAndFlush(updateComment);
        }

        return null;
    }

    public Optional<Comment> delete(UUID id) {
        var comment = getById(id);
        commentRepository.deleteById(id);
        return comment;
    }
}
