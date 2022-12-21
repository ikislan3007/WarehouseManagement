package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.entity.UserEntity;
import com.example.warehousemanagement.exceptionsHandler.exceptions.IncorrectPasswordException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.UserNotFoundException;
import com.example.warehousemanagement.repository.UserRepository;
import com.example.warehousemanagement.security.JwtUtil;
import com.example.warehousemanagement.security.dto.AuthenticationRequest;
import com.example.warehousemanagement.security.dto.AuthenticationResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/login")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        UserEntity userEntity = userRepository.findByUsername(authenticationRequest.getUsername());
        if (!(userEntity == null)) {

            if (!(userEntity.getPassword().equals(authenticationRequest.getPassword()))) {
                throw new IncorrectPasswordException();
            }

        } else {
            throw new UserNotFoundException(authenticationRequest.getUsername());
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final String jwt = jwtTokenUtil.createToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
