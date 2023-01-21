package com.example.warehousemanagement.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.example.warehousemanagement.dto.BaseDto;
import com.example.warehousemanagement.entity.BaseEntity;
import com.example.warehousemanagement.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
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
    @Operation(summary = "Create a New Object", description = "Create a new Object", tags = {"Beers"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Object Created"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "409", description = "Conflict")})

    default ResponseEntity<D> save(@Valid @RequestBody D dto) {
        return ResponseEntity.status(CREATED).body(crudService().save(dto));
    }

    @GetMapping(path = "{id}", produces = {"application/json"})
    @Operation(summary = "Get by Id", description = "Get a single object by its Id value.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found by Id", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Not Found")})
    default ResponseEntity<D> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().findById(id));
    }

    @GetMapping(path = "/all", produces = {"application/json"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of Objects", content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "No Objects Found")})
    default ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(crudService().findAll());
    }

    @PutMapping(path = "{id}", produces = {"application/json"})
    @Operation(summary = "Update Object by Id", description = "Update object by its ID value.", tags = {"Beers"})
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Object Updated"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "409", description = "Conflict")})
    default ResponseEntity<D> edit(@PathVariable(name = "id") Long id, @Valid @RequestBody D dto) {
        D dtoEdit = crudService().update(id, dto);
        return ResponseEntity.ok(dtoEdit);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Delete by Id", description = "Delete resource by its Id value.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource Deleted"),
        @ApiResponse(responseCode = "404", description = "Not Found")})
    default ResponseEntity<D> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(crudService().deleteById(id));
    }
}
