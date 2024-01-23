package com.devgym.gymmanager.orderitem.repository;

import com.devgym.gymmanager.orderitem.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<OrderItem, Long> {

}
