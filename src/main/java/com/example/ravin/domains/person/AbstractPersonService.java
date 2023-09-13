package com.example.ravin.domains.person;

import com.example.ravin.domains.dtos.request.PersonRequestDto;
import com.example.ravin.domains.user.UserService;
import com.example.ravin.exceptions.CpfAlreadyExistsException;
import com.example.ravin.exceptions.LoginAlreadyExists;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public abstract class AbstractPersonService {
    private final PersonRepository personRepository;
    private final UserService userService;

    public void validate(PersonRequestDto person) {
        if (personRepository.existsByCpf(person.getCpf())) {
            throw new CpfAlreadyExistsException();
        }

        if (userService.existsByLogin(person.getUser().getLogin())) {
            throw new LoginAlreadyExists();
        }
    }

    public void validate (PersonRequestDto person, UUID excludedId) {
        if (personRepository.existsByCpfAndIdNot(person.getCpf(), excludedId)) {
            throw new CpfAlreadyExistsException();
        }

        if (personRepository.existsByUserLoginAndIdNot(person.getUser().getLogin(), excludedId)) {
            throw new LoginAlreadyExists();
        }
    }
}
