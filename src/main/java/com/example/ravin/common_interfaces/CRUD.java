package com.example.ravin.common_interfaces;

import java.util.List;

public interface CRUD <T, U, V> {
    List<U> findAll();
    U findById(V id);
    U save(T t);
    U update(T t, V id);
    void deleteById(V id);
}
