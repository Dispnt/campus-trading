package com.dispnt.mall.repository;

import com.dispnt.mall.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {


    List<Item> findByNameContaining(String searchContent);

    Item findById(int itemId);


}
