package com.git.domain.item;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String itemName;
    private int price;
    @Enumerated(STRING)
    private ItemCode itemCode;
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private List<ItemCondition> itemCondition = new ArrayList<>();

    @Builder
    public Item(String itemName, int price, ItemCode itemCode, List<ItemCondition> itemCondition) {
        this.itemName = itemName;
        this.price = price;
        this.itemCode = itemCode;
        this.itemCondition = itemCondition;
    }

    protected Item() {
    }

    public void addItemCondition(ItemCondition itemCondition) {
        this.getItemCondition().add(itemCondition);
    }

    public void update(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }
}






























