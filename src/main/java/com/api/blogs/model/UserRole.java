package com.api.blogs.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String roleName;
    private Instant dateCreated = Instant.now();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
