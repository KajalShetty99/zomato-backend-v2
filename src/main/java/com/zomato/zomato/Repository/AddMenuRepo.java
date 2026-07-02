package com.zomato.zomato.Repository;
import java.util.List;
import com.zomato.zomato.model.AddMenu;
import com.zomato.zomato.model.RestaurantRegister;
import com.zomato.zomato.model.UserRegisteration;

import org.springframework.data.jpa.repository.JpaRepository;

    public interface AddMenuRepo extends JpaRepository<AddMenu, Integer>{
        List<AddMenu> findByDishesContainingIgnoreCase(String dishes);
        AddMenu findByRestaurant(RestaurantRegister restaurant);
    }
