package com.example.ravin.domains.person.employee;

import java.util.List;

public interface IEmployee <T> {
    List<T> findAllAvailableWaiters();
}
