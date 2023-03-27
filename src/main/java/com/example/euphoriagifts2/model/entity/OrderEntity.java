package com.example.euphoriagifts2.model.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "orders")
public class
OrderEntity extends BaseEntity {

    private Set<GiftEntity> gifts;

    private UserEntity customer;
    private BigDecimal totalPrice;

    private LocalDateTime orderTime;

    private AddressEntity deliveryAddress;


    public OrderEntity() {
    }

    @ManyToOne(optional = false)
    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    @ManyToMany(fetch = FetchType.EAGER)
  //  @Fetch(value = FetchMode.SUBSELECT - Works both ways)
    public Set<GiftEntity> getGifts() {
        return gifts;
    }

    public void setGifts(Set<GiftEntity> gifts) {
        this.gifts = gifts;
    }


    @Column(nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    public AddressEntity getDeliveryAddress() {
        return deliveryAddress;
    }


    public void setDeliveryAddress(AddressEntity deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Column(name = "order_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}
