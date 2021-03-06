package com.dispnt.mall.repository;

import com.dispnt.mall.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
