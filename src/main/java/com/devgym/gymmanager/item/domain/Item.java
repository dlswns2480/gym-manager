package com.devgym.gymmanager.item.domain;

import com.devgym.gymmanager.orderitem.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "item_category")
    private Category category;
    @Column(name = "item_name", unique = true)
    private String itemName;
    @Column(name = "item_price")
    private int price;

    public Item(Category category, String itemName, int price) {
        this.category = category;
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Item item = (Item) object;
        return price == item.price && category == item.category && Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, itemName, price);
    }
}
