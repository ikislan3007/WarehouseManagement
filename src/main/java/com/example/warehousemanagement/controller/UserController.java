package com.example.warehousemanagement.controller;

import com.example.warehousemanagement.dto.UserDto;
import com.example.warehousemanagement.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/registration")
public interface UserController extends CrudController<UserDto, UserEntity> {

}
