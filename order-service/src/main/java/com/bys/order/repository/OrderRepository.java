package com.bys.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bys.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
