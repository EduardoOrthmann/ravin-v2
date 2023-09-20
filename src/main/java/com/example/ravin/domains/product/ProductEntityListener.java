package com.example.ravin.domains.product;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityListener {
    @PrePersist
    public void prePersist(Product product) {
        product.setActive(true);
    }
}
