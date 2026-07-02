package com.zomato.zomato.Repository;
import java.util.Optional;
import com.zomato.zomato.model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

    public interface AddToCartRepo extends JpaRepository<Cart, Integer>{
     //     Optional<Cart> findByUserIdAndFoodId(Long userId, Long foodItemId);
    }

