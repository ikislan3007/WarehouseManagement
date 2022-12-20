package com.example.warehousemanagement.security.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class AuthenticationResponse {

    String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }


}
