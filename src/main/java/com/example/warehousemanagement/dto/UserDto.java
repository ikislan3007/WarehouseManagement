package com.example.warehousemanagement.dto;

import com.example.warehousemanagement.base.BaseDto;
import com.example.warehousemanagement.infrastracture.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends BaseDto {


    @Size(min = 5, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    @NotBlank
    String username;

    @ValidPassword
    @NotBlank
    String password;

    @NotBlank(message = "Email can not be blank")
    @Email
    String email;

    @Pattern(regexp = "^[2-9]\\d{2}-\\d{3}-\\d{3}$")
    String phone;
}
