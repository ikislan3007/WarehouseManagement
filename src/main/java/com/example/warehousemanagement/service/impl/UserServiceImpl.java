package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.repository.BaseRepository;
import com.example.warehousemanagement.mapper.ResourceEntityTransformer;
import com.example.warehousemanagement.dto.UserDto;
import com.example.warehousemanagement.entity.UserEntity;
import com.example.warehousemanagement.mapper.UserMapper;
import com.example.warehousemanagement.repository.UserRepository;
import com.example.warehousemanagement.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    final UserRepository repository;
    final UserMapper mapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public BaseRepository<UserEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<UserDto, UserEntity> resourceTransformer() {
        return mapper;
    }

    @Override
    public UserDto save(UserDto dto) {
    String password = bCryptPasswordEncoder.encode(dto.getPassword());
    dto.setPassword(password);
        return UserService.super.save(dto);
    }
}
