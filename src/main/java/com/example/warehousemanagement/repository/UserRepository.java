package com.example.warehousemanagement.repository;

import com.example.warehousemanagement.base.BaseRepository;
import com.example.warehousemanagement.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

}
