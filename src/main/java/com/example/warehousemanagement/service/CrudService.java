package com.example.warehousemanagement.service;

import com.example.warehousemanagement.mapper.ResourceEntityTransformer;
import com.example.warehousemanagement.dto.BaseDto;
import com.example.warehousemanagement.entity.BaseEntity;
import com.example.warehousemanagement.repository.BaseRepository;
import com.example.warehousemanagement.exceptionsHandler.exceptions.ResourceNotFoundException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CrudService<D extends BaseDto, E extends BaseEntity> {

    BaseRepository<E, Long> repository();

    ResourceEntityTransformer<D, E> resourceTransformer();

    default D save(D dto) {
        return resourceTransformer().transformToResource(repository().save(resourceTransformer().transformToEntity(dto)));
    }

    default Page<D> findAll(Pageable pageable) {
        final ResourceEntityTransformer<D, E> transformer = resourceTransformer();
        return repository().findAll(pageable).map(transformer::transformToResource);
    }

    default D findById(Long id) {
        E entity = findByIdEntity(id);
        return resourceTransformer().transformToResource(entity);
    }


    default D update(Long id, D dto) {
        E entityDb = repository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        E entityForUpdate = resourceTransformer().transformToEntity(dto);
        entityForUpdate.setId(entityDb.getId());
        return resourceTransformer().transformToResource(repository().save(entityForUpdate));
    }

    default E findByIdEntity(Long id) {
        return repository().findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    default D deleteById(Long id) {
        Optional<E> optional = repository().findById(id);
        if (optional.isPresent()) {
            repository().deleteById(id);
        }
        return resourceTransformer().transformToResource(optional.orElseThrow(
            () -> new ResourceNotFoundException(id)));
    }


}
