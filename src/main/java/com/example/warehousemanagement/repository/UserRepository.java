package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
