package com.example.euphoriagifts2.repository;

import com.example.euphoriagifts2.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>  {


}
