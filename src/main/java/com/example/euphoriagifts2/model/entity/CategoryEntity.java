package com.example.euphoriagifts2.model.entity;

import com.example.euphoriagifts2.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    private CategoryNameEnum categoryName;
    private String description;

    public CategoryEntity() {
    }
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryNameEnum categoryName) {
        this.categoryName = categoryName;
    }

    @Lob
    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
