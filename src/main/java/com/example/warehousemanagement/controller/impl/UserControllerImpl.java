package com.example.warehousemanagement.controller.impl;

import com.example.warehousemanagement.controller.UserController;
import com.example.warehousemanagement.dto.UserDto;
import com.example.warehousemanagement.entity.UserEntity;
import com.example.warehousemanagement.service.CrudService;
import com.example.warehousemanagement.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerImpl implements UserController {

    final UserService service;

    @Override
    public CrudService<UserDto, UserEntity> crudService() {
        return service;
    }


}
