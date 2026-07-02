package com.zomato.zomato.model;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "FavoriteRestaurant")
public class FavoriteRestaurant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegisteration user;

    @ManyToOne 
    @JoinColumn(name = "restaurant_id")
    private RestaurantRegister restaurant;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RestaurantRegister getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(RestaurantRegister restaurant) {
        this.restaurant = restaurant;
    }
    public UserRegisteration getUser() {
        return user;
    }
    public void setUser(UserRegisteration user) {
        this.user = user;
    }
}
