package com.example.warehousemanagement.mapper;

public interface ResourceEntityTransformer<R, E> {

    R transformToResource(E entity);

    E transformToEntity(R resource);

}
