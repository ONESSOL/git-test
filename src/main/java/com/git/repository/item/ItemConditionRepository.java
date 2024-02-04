package com.git.repository.item;

import com.git.domain.item.ItemCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemConditionRepository extends JpaRepository<ItemCondition, Long> {

}
