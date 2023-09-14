package com.example.ravin.domains.dtos.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String name;

    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
    private String description;

    @NotNull(message = "O código não pode ser nulo")
    @NotBlank(message = "O código não pode ser vazio")
    @Size(max = 20, message = "O código deve ter no máximo 20 caracteres")
    private String code;

    @NotNull(message = "O preço de custo não pode ser nulo")
    @Positive(message = "O preço de custo deve ser positivo")
    @JsonProperty("cost_price")
    private Double costPrice;

    @NotNull(message = "O preço de venda não pode ser nulo")
    @Positive(message = "O preço de venda deve ser positivo")
    @JsonAlias({"sale_price", "price"})
    private Double salePrice;

    @JsonProperty("time_to_prepare")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeToPrepare;
}
