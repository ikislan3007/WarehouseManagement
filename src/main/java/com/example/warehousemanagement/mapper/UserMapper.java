package com.example.warehousemanagement.mapper;

import com.example.warehousemanagement.base.ResourceEntityTransformer;
import com.example.warehousemanagement.dto.UserDto;
import com.example.warehousemanagement.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper extends ResourceEntityTransformer<UserDto, UserEntity> {

    UserDto transformToResource(UserEntity entity);

    UserEntity transformToEntity(UserDto resource);
}
