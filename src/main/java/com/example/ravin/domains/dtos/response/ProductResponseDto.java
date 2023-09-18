package com.example.ravin.domains.dtos.response;

import com.example.ravin.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private UUID id;

    private String name;

    private String description;

    private ProductCategory category;

    private String code;

    @JsonProperty("cost_price")
    private Double costPrice;

    @JsonProperty("sale_price")
    private Double salePrice;

    @JsonProperty("time_to_prepare")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeToPrepare;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("created_date")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("last_modified_date")
    private LocalDateTime lastModifiedDate;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("last_modified_by")
    private String lastModifiedBy;
}
