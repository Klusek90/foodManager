package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
