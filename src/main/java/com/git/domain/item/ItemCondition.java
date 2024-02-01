package com.git.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class ItemCondition {

    @Id @GeneratedValue
    @Column(name = "item_condition_id")
    private Long id;
    private String color;
    private String size;
    private int quantity;

    @Builder
    public ItemCondition(String color, String size, int quantity) {
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    protected ItemCondition() {
    }
}
