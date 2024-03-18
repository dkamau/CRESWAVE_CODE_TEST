package com.api.blogs.config;

import java.time.Instant;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.blogs.model.BlogPost;
import com.api.blogs.repository.BlogPostRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class DBSeederConfig {

    private final BlogPostRepository blogPostRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            BlogPost blogPost1 = new BlogPost(null, "Hello", "This is my first", Instant.now(),
                    Instant.now(), true, null, null);
            BlogPost blogPost2 = new BlogPost(null, "Bye", "This is my second", Instant.now(),
                    Instant.now(), false, null, null);

            blogPostRepository.saveAll(List.of(blogPost1, blogPost2));
        };
    }
}
