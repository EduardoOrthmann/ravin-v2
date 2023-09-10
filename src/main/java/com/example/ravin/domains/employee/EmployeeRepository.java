package com.example.ravin.domains.employee;

import com.example.ravin.domains.person.PersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID>, PersonRepository {
    @Query("SELECT e FROM Employee e WHERE e.position = :position AND e.isAvailable = true")
    List<Employee> findAllAvailableWaiters();
}
