package com.example.ravin.domains.product;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.domains.dtos.request.ProductRequestDto;
import com.example.ravin.domains.dtos.response.ProductResponseDto;

import java.util.UUID;

public interface ProductService extends CRUD<ProductRequestDto, ProductResponseDto, UUID> {
}
