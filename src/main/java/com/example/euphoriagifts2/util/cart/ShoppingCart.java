package com.example.euphoriagifts2.util.cart;

import com.example.euphoriagifts2.model.entity.AddressEntity;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingCart implements DisposableBean {
    private List<ShoppingCartEntity> gifts;
    private AddressEntity deliveryAddress;
    private Boolean isPaid;

    public ShoppingCart() {
        this.gifts = new ArrayList<>();
    }

    public List<ShoppingCartEntity> getGifts() {
        return gifts;
    }

    public void setGifts(List<ShoppingCartEntity> gifts) {
        this.gifts = gifts;
    }

    public AddressEntity getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressEntity deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Override
    public void destroy() throws Exception {
        this.setPaid(false);
        this.setDeliveryAddress(null);
        this.setGifts(new ArrayList<>());
    }

}
