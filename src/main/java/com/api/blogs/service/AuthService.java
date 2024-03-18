package com.api.blogs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.stereotype.Service;

import com.api.blogs.config.security.JwtIssuer;
import com.api.blogs.dto.auth.LoginRequest;
import com.api.blogs.dto.auth.LoginResponse;
import com.api.blogs.dto.auth.SignupRequest;
import com.api.blogs.dto.auth.SignupResponse;
import com.api.blogs.enums.RoleEnum;
import com.api.blogs.model.User;
import com.api.blogs.model.UserRole;
import com.api.blogs.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtIssuer jwtIssuer;

    public LoginResponse login(LoginRequest loginRequest) {
        var token = jwtIssuer.issue(UUID.randomUUID(), loginRequest.getEmail(), List.of("USER"));
        return LoginResponse.builder().accessToken(token).build();
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        User prob = new User();
        prob.setEmail(signupRequest.getEmail());

        if (userExists(signupRequest.getEmail())) {
        }
        // throw new Exception("UserExists");

        List<UserRole> userRoles = new ArrayList<UserRole>();

        for (String role : signupRequest.getRoles()) {
            if (EnumUtils.isValidEnum(RoleEnum.class, role))
                if (!userRoles.stream().anyMatch(n -> n.getRoleName().equals(role)))
                    userRoles.add(new UserRole(null, role, null, null));
        }

        User user = userRepository.saveAndFlush(new User(null, signupRequest.getFullName(), signupRequest.getEmail(),
                signupRequest.getPassword(), null, userRoles, null, null));

        return SignupResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roles(signupRequest.getRoles())
                .build();
    }

    public List<SignupResponse> listUsers() {
        var users = userRepository.findAll();
        List<SignupResponse> response = new ArrayList<SignupResponse>();

        for (User user : users) {
            List<String> userRoles = user.getUserRoles().stream().map(n -> n.getRoleName()).toList();
            response.add(new SignupResponse(user.getId(), user.getFullName(), user.getEmail(), userRoles));
        }

        return response;
    }

    private Boolean userExists(String email) {
        User user = new User();
        user.setEmail(email);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("model", new GenericPropertyMatcher().ignoreCase())
                .withMatcher("email", new GenericPropertyMatcher().exact());

        Example<User> example = Example.of(user, matcher);

        boolean exists = userRepository.exists(example);

        return exists;
    }
}
