package com.api.blogs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.blogs.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
