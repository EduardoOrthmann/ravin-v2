package com.example.ravin.domains.customer;

import com.example.ravin.domains.person.PersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>, PersonRepository {
}
