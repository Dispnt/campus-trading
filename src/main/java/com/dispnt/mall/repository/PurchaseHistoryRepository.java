package com.dispnt.mall.repository;

import com.dispnt.mall.model.Item;
import com.dispnt.mall.model.Purchase_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<Purchase_history, Integer>{
    List<Purchase_history> findByItemIdAndUserId(int itemId, int userId);

    @Modifying
    @Query("delete from Purchase_history p where p.id = ?1")
    void delete(int id);
}
