package com.example.ravin.domains.person;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityListener {
    @PrePersist
    public void prePersist(Person person) {
        person.setActive(true);
    }
}
