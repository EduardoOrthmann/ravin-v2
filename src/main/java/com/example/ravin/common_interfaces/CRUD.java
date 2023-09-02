package com.example.ravin.common_interfaces;

import java.util.List;

/*
    * T: Type of the object to be saved (request dto)
    * U: Type of the object to be returned (response dto)
    * V: Type of the id of the object (UUID)
 */
public interface CRUD <T, U, V> {
    List<U> findAll();
    U findById(V id);
    U save(T request);
    U update(V id, T request);
    void deleteById(V id);
}
