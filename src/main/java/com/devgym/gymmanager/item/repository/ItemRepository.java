package com.devgym.gymmanager.item.repository;

import com.devgym.gymmanager.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
