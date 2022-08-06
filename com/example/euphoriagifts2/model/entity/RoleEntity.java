package com.example.euphoriagifts2.model.entity;

import com.example.euphoriagifts2.model.entity.enums.RoleNameEnum;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    private RoleNameEnum roleName;


    public RoleEntity() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public RoleNameEnum getName() {
        return roleName;
    }

    public RoleEntity setName(RoleNameEnum roleName) {
        this.roleName = roleName;
        return this;
    }

}
