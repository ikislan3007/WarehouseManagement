package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.dto.ProductDto;
import com.example.warehousemanagement.entity.ImageEntity;
import com.example.warehousemanagement.entity.ProductEntity;
import com.example.warehousemanagement.entity.ProductEntity.Category;
import com.example.warehousemanagement.exceptionsHandler.exceptions.ContentTypeNotSupportedException;
import com.example.warehousemanagement.exceptionsHandler.exceptions.ResourceNotFoundException;
import com.example.warehousemanagement.mapper.ProductMapper;
import com.example.warehousemanagement.mapper.ResourceEntityTransformer;
import com.example.warehousemanagement.repository.BaseRepository;
import com.example.warehousemanagement.repository.ImageRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.service.ProductService;
import com.example.warehousemanagement.until.ImageUtility;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    final ProductRepository repository;
    final ProductMapper mapper;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public BaseRepository<ProductEntity, Long> repository() {
        return repository;
    }

    @Override
    public ResourceEntityTransformer<ProductDto, ProductEntity> resourceTransformer() {
        return mapper;
    }

    @Override
    public List<ProductDto> getByCode(String code) {

        List<ProductEntity> products = repository.getByCode(code);

        return products.stream().map(product -> mapper.transformToResource(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByCategory(Category category) {
        List<ProductEntity> products = repository.getByCategory(category);
        return products.stream().map(product -> mapper.transformToResource(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getByCategoryAndProductName(Category category, Optional<String> productName) {


        List<ProductEntity> products = repository.findAllByCategoryAndProductNameStartsWith(category, productName);

        return products.stream().map(product -> mapper.transformToResource(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto save(ProductDto dto, Optional<MultipartFile> file) throws IOException {

        if (!(file.isPresent())) {
            return ProductService.super.save(dto);
        } else {
            ImageEntity image;
            if (file.get().getContentType().equals("image/jpeg") || file.get().getContentType().equals("image/png") || file.get().getContentType()
                .equals("image/jpg")) {
                image = ImageEntity.builder()
                    .name(file.get().getOriginalFilename())
                    .type(file.get().getContentType())
                    .image(ImageUtility.compressImage(file.get().getBytes())).build();

            } else {
                throw new ContentTypeNotSupportedException();
            }
            imageRepository.save(image);

            dto.setUploadFile(image.getName());
            return mapper.transformToResource(repository.save(mapper.transformToEntity(dto)));
        }
    }

    @Override
    public ProductDto edit(ProductDto dto, Optional<MultipartFile> file) throws IOException {

        ProductEntity entityDb = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException(dto.getId()));


        ProductEntity entityForUpdate = mapper.transformToEntity(dto);
        entityForUpdate.setId(entityDb.getId());


        if (!(file.isPresent())) {
            return ProductService.super.save(dto);
        } else {
            ImageEntity image = null;
            if (file.get().getContentType().equals("image/jpeg") || file.get().getContentType().equals("image/png") || file.get().getContentType()
                .equals("image/jpg")) {
                image = ImageEntity.builder()
                    .name(file.get().getOriginalFilename())
                    .type(file.get().getContentType())
                    .image(ImageUtility.compressImage(file.get().getBytes())).build();

            }
            imageRepository.save(image);

            entityForUpdate.setUploadFile(image.getName());
            return mapper.transformToResource(repository.save(entityForUpdate));
        }

    }
}
