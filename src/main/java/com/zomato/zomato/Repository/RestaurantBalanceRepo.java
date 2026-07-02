package com.zomato.zomato.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zomato.zomato.model.RestaurantRegister;
import com.zomato.zomato.model.RestaurantWallet;
import java.util.Optional;

public interface RestaurantBalanceRepo extends JpaRepository<RestaurantWallet, Integer>{
        RestaurantWallet findByRestaurant(RestaurantRegister restaurant); 
}
