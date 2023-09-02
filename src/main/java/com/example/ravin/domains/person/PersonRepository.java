package com.example.ravin.domains.person;

public interface PersonRepository {
    boolean existsByCpf(String cpf);
}
