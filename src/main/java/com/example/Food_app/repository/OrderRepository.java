package com.example.Food_app.repository;

import com.example.Food_app.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails,Integer> {
}
