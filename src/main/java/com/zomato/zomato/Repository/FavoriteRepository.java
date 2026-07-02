package com.zomato.zomato.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zomato.zomato.model.FavoriteRestaurant;

    public interface FavoriteRepository extends JpaRepository<FavoriteRestaurant, Integer>{
         
    }

