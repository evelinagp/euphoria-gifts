package com.example.euphoriagifts2.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offline_shops")
public class OfflineShopEntity extends BaseEntity {
    private String name;
    private TownEntity townEntity;

    public OfflineShopEntity() {
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne(optional = false)
    public TownEntity getTown() {
        return townEntity;
    }

    public void setTown(TownEntity townEntity) {
        this.townEntity = townEntity;
    }

}
