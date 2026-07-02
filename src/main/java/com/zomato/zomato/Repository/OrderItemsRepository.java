package com.zomato.zomato.Repository;
import com.zomato.zomato.model.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

 public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer>{
    List<OrderItem> findByMenuId(Integer menuId);
}
