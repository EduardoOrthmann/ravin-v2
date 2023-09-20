package com.example.ravin.domains.table;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.enums.TableStatus;

import java.util.List;

public interface TableService<T, U, V> extends CRUD<T, U, V> {
    List<U> findAllByStatus(TableStatus status);
    List<U> findAllAvailable();
    void reserveTable(V id);
    void freeTable(V id);
}
