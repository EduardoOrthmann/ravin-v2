package com.example.ravin.domains.product.impl;

import com.example.ravin.domains.dtos.mapper.ProductMapper;
import com.example.ravin.domains.dtos.request.ProductRequestDto;
import com.example.ravin.domains.dtos.response.ProductResponseDto;
import com.example.ravin.domains.product.Product;
import com.example.ravin.domains.product.ProductRepository;
import com.example.ravin.domains.product.ProductService;
import com.example.ravin.utils.constants.ErrorMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public List<ProductResponseDto> findAll() {
        return mapper.toResponseList(productRepository.findAll());
    }

    @Override
    public ProductResponseDto findById(UUID id) {
        return productRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND));
    }

    @Override
    public ProductResponseDto save(ProductRequestDto request) {
        return mapper.toResponse(
                productRepository.save(mapper.toEntity(request))
        );
    }

    @Override
    public ProductResponseDto update(UUID id, ProductRequestDto request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND));

        return mapper.toResponse(
                productRepository.save(mapper.updateEntity(product, request))
        );
    }

    @Override
    public void deleteById(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }

        productRepository.deleteById(id);
    }
}
