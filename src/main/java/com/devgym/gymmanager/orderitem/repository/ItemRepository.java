package com.devgym.gymmanager.orderitem.repository;

import com.devgym.gymmanager.orderitem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem, Long> {
}
