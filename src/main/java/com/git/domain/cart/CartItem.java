package com.git.domain.cart;

import com.git.domain.item.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class CartItem {

    @Id @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private String color;
    private String size;
    private int count;

    @Builder
    public CartItem(Item item, Cart cart, String color, String size, int count) {
        this.item = item;
        this.cart = cart;
        this.color = color;
        this.size = size;
        this.count = count;
    }

    protected CartItem() {
    }

}
