package com.example.euphoriagifts2.util.cart;

import com.example.euphoriagifts2.model.entity.GiftEntity;

import java.math.BigDecimal;

public class ShoppingCartEntity {
    private GiftEntity gift;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;

    public ShoppingCartEntity() {
    }

    public GiftEntity getGift() {
        return gift;
    }

    public void setGift(GiftEntity gift) {
        this.gift = gift;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return new BigDecimal(quantity * Double.parseDouble(price.toString()));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = new BigDecimal(Double.parseDouble(price.toString()));
    }

}
