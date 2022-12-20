package com.example.warehousemanagement.security.dto;

import com.example.warehousemanagement.validation.ValidPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthenticationRequest {

    @Size(min = 5, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    @NotBlank
    String username;

    @ValidPassword
    @NotBlank
    String password;
}
