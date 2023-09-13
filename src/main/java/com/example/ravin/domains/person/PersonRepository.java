package com.example.ravin.domains.person;

import java.util.UUID;

public interface PersonRepository {
    boolean existsByCpf(String cpf);
    boolean existsByCpfAndIdNot(String cpf, UUID id);
    boolean existsByUserLoginAndIdNot(String login, UUID id);
}
