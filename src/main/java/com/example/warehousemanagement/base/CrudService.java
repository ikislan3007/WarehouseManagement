package com.example.warehousemanagement.base;

import com.example.warehousemanagement.base.BaseDto;
import com.example.warehousemanagement.base.BaseEntity;
import com.example.warehousemanagement.exception.ResourceNotFoundException;
import com.example.warehousemanagement.base.ResourceEntityTransformer;
import com.example.warehousemanagement.base.BaseRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    default List<D> editAll(List<D> dtoList) {
        Set<Long> ids = repository().findExistingIds(dtoList.stream().map(dto -> dto.getId()).collect(Collectors.toList()));
        return saveAll(dtoList.stream().filter(dto -> ids.contains(dto.getId())).collect(Collectors.toList()));
    }

    default List<D> saveAll(List<D> dtoList) {
        return repository().saveAll(dtoList.stream().map(dto -> resourceTransformer().transformToEntity(dto)).collect(Collectors.toList())).stream()
            .map(e -> resourceTransformer().transformToResource(e)).collect(Collectors.toList());
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