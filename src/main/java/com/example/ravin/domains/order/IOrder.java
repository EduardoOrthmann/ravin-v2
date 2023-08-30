package com.example.ravin.domains.order;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.enums.OrderStatus;

import java.util.List;

public interface IOrder <T, U, V> extends CRUD<T, U, V> {
    List<U> findAllByStatus(OrderStatus status);
    void closeOrder(V id);
}
