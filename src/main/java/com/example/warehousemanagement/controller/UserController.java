package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.dto.UserDto;
import com.example.warehousemanagement.entity.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/warehouse/registration")
@Tag(name = "Registration")
public interface UserController extends CrudController<UserDto, UserEntity> {


}
