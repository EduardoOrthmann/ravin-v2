package com.example.ravin.domains.dtos.custom_serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CpfSerializer extends JsonSerializer<String> {
    private static final String CPF_REGEX = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String cpf = s.trim().replaceAll("\\D", "");

        jsonGenerator.writeString(cpf.replaceAll(CPF_REGEX, "$1.$2.$3-$4"));
    }
}
