package com.example.warehousemanagement.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.example.warehousemanagement.dto.BaseDto;
import com.example.warehousemanagement.entity.BaseEntity;
import com.example.warehousemanagement.service.CrudService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Validated
public interface CrudController<D extends BaseDto, E extends BaseEntity> {

    CrudService<D, E> crudService();

    @PostMapping(consumes = {"application/json"})
    default ResponseEntity<D> save(@Valid @RequestBody D dto) {
        return ResponseEntity.status(CREATED).body(crudService().save(dto));
    }

    @GetMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().findById(id));
    }

    @GetMapping("/all")
    default ResponseEntity<Page<D>> findAll(Pageable pageable) {
        return ResponseEntity.ok(crudService().findAll(pageable));
    }

    @PutMapping(path = "{id}", produces = {"application/json"})
    default ResponseEntity<D> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody D dto) {

        D dtoEdit = crudService().update(id, dto);
        return ResponseEntity.ok(dtoEdit);
    }

    @DeleteMapping(path = "{id}")
    default ResponseEntity<D> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().deleteById(id));
    }
}
