package com.zomato.zomato.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zomato.zomato.model.OrderCancellations;

public interface OrderCancelRepo extends JpaRepository<OrderCancellations, Integer>{
         
}