package com.zomato.zomato.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "Restaurant_income")
public class RestaurantWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    private double balance;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantRegister restaurant;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
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
}
