package com.example.ravin.domains.order.order_details;

import com.example.ravin.common_interfaces.CRUD;
import com.example.ravin.enums.OrderDetailsStatus;

import java.util.List;

public interface OrderDetailsService<T, U, V> extends CRUD<T, U, V> {
    List<U> findAllByStatus(OrderDetailsStatus status);
    void cancelOrderDetail(V id);
}
