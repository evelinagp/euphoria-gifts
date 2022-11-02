package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>  {

    boolean existsOrderEntityByGiftsIsNotContaining(Long id);

}
