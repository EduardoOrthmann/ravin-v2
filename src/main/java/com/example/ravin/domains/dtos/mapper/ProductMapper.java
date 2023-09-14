package com.example.ravin.domains.dtos.mapper;

import com.example.ravin.domains.dtos.request.ProductRequestDto;
import com.example.ravin.domains.dtos.response.ProductResponseDto;
import com.example.ravin.domains.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductRequestDto, ProductResponseDto> {
    public ProductMapper(ModelMapper mapper) {
        super(mapper);
    }

    @Override
    Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    Class<ProductRequestDto> getRequestClass() {
        return ProductRequestDto.class;
    }

    @Override
    Class<ProductResponseDto> getResponseClass() {
        return ProductResponseDto.class;
    }
}
