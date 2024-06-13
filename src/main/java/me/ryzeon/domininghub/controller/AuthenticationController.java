package me.ryzeon.domininghub.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import me.ryzeon.domininghub.dto.auth.SignInRequest;
import me.ryzeon.domininghub.dto.auth.SignInResponse;
import me.ryzeon.domininghub.dto.auth.SignUpRequest;
import me.ryzeon.domininghub.dto.auth.SignUpResponse;
import me.ryzeon.domininghub.entity.MessageResponse;
import me.ryzeon.domininghub.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alex Avila Asto - A.K.A (Ryzeon)
 * Project: domining-hub
 * Date: 6/11/24 @ 18:27
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
@AllArgsConstructor
public class AuthenticationController {

    private final IUserService userService;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sign in successfully",
                    content = @Content(schema = @Schema(implementation = SignInResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))
            )
    })
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        var details = userService.signIn(signInRequest.usernameOrEmail(), signInRequest.password()).orElseThrow(() -> new RuntimeException("An error occurred while signing in"));
        var signInResponse = SignInResponse.fromDetails(details);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Sign up successfully",
                    content = @Content(schema = @Schema(implementation = SignUpResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid credentials",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))
            )
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        var user = userService.signUp(signUpRequest).orElseThrow(() -> new RuntimeException("An error occurred while signing up"));
        var signUpResponse = SignUpResponse.fromUser(user);
        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
    }
}
