package com.zomato.zomato.Repository;
import com.zomato.zomato.model.Cart;
import com.zomato.zomato.model.OrderHeader;

import org.springframework.data.jpa.repository.JpaRepository;

    public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Integer>{
         
    }
