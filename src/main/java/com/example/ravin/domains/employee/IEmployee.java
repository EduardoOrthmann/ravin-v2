package com.example.ravin.domains.employee;

import com.example.ravin.common_interfaces.CRUD;

import java.util.List;

public interface IEmployee <T, U, V> extends CRUD<T, U, V> {
    List<U> findAllByAvailability(boolean available);
}
