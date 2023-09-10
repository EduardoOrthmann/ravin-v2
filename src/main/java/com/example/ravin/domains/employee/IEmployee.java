package com.example.ravin.domains.employee;

import java.util.List;

public interface IEmployee <T> {
    List<T> findAllAvailableWaiters();
}
