package com.dispnt.mall.repository;

import com.dispnt.mall.model.Item;
import com.dispnt.mall.model.Purchase_history;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<Purchase_history, Integer>{
    List<Purchase_history> findByItemIdAndUserId(int itemId, int userId);

//    void deleteById(int id);
}
