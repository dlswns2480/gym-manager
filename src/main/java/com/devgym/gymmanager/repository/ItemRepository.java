package com.devgym.gymmanager.repository;

import com.devgym.gymmanager.domain.entity.OrderItem;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;

public interface ItemRepository extends JpaAttributeConverter<OrderItem, Long> {
}
