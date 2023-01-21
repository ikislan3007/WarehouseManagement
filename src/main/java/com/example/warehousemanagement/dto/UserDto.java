package com.example.warehousemanagement.dto;

import com.example.warehousemanagement.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Pattern(regexp = "^\\d{4}-\\d{3}-\\d{3}$")
    String phone;
}
