package com.example.warehousemanagement.controller.impl;

import com.example.warehousemanagement.controller.AuthenticationController;
import com.example.warehousemanagement.entity.UserEntity;
import com.example.warehousemanagement.exceptionsHandler.exceptions.UserNotFoundException;
import com.example.warehousemanagement.repository.UserRepository;
import com.example.warehousemanagement.security.JwtUtil;
import com.example.warehousemanagement.security.dto.AuthenticationRequest;
import com.example.warehousemanagement.security.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationControllerImpl implements AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping(consumes = {"application/json"})
    @Operation(summary = "Create a New Object", description = "Create a new Object", tags = {"Beers"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Object Created"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "409", description = "Conflict")})
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        UserEntity userEntity = userRepository.findByUsername(authenticationRequest.getUsername());
        if (userEntity == null) {
            throw new UserNotFoundException(authenticationRequest.getUsername());
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword())
        );

        final String jwt = jwtTokenUtil.createToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
