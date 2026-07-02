package com.zomato.zomato.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zomato.zomato.model.RestaurantRegister;
import com.zomato.zomato.model.RestaurantWalletTransaction;

public interface RestaurantTransactionRepo extends JpaRepository<RestaurantWalletTransaction,Integer>{
        
}