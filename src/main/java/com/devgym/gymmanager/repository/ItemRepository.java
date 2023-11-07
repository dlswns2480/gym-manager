package com.devgym.gymmanager.repository;

import com.devgym.gymmanager.domain.entity.OrderItem;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<OrderItem, Long> {
}
