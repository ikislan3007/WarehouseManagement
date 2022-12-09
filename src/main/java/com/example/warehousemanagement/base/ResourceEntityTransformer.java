package com.example.warehousemanagement.base;

public interface ResourceEntityTransformer<R, E> {

    R transformToResource(E entity);

    E transformToEntity(R resource);

}
