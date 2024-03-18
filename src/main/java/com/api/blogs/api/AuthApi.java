package com.api.blogs.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.blogs.dto.ResponseError;
import com.api.blogs.dto.auth.LoginRequest;
import com.api.blogs.dto.auth.LoginResponse;
import com.api.blogs.dto.auth.SignupRequest;
import com.api.blogs.dto.auth.SignupResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Authentication Api")
public interface AuthApi {

        @Operation(summary = "Login", description = "User Login")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) })
        })
        ResponseEntity<?> login(@RequestBody @Validated LoginRequest loginRequest);

        @Operation(summary = "Signup", description = "User Signup")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = SignupResponse.class)) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) })
        })
        ResponseEntity<?> signup(@RequestBody @Validated SignupRequest signupRequest);

        @Operation(summary = "List", description = "List All Users")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = List.class, subTypes = {
                                                        SignupResponse.class })) }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)) })
        })
        ResponseEntity<List<SignupResponse>> list();
}
