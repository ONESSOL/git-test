package com.git.repository.item;

import com.git.domain.item.Item;
import com.git.domain.item.ItemCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByItemName(String itemName, Pageable pageable);
    Page<Item> findByItemCode(ItemCode itemCode, Pageable pageable);
}
