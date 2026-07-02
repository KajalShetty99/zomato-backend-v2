package com.zomato.zomato.Repository;
import com.zomato.zomato.model.RestaurantRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

    public interface restaurantRepository extends JpaRepository<RestaurantRegister, Integer>{
         List<RestaurantRegister> findByRestaurantNameContainingIgnoreCase(String name);
         List<RestaurantRegister> findByRatingGreaterThanEqual(Double rating);
        //  List<RestaurantRegister> findByRating(Double rating);
    }
