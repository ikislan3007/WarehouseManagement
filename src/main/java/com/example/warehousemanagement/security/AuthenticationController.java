package com.example.warehousemanagement.security;

import com.example.warehousemanagement.security.dto.AuthenticationRequest;
import com.example.warehousemanagement.security.dto.AuthenticationResponse;
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
    JwtUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final String jwt = jwtTokenUtil.createToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
