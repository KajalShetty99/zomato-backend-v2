package com.zomato.zomato.model;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegisteration user;

    @ManyToOne  // One user has many cart items.
    @JoinColumn(name = "food_id")
    private AddMenu food;
    
    private int quantity;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
   
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public UserRegisteration getUser() {
        return user;
    }
    public void setUser(UserRegisteration user) {
        this.user = user;
    }
    public AddMenu getFood() {
        return food;
    }
    public void setFood(AddMenu food) {
        this.food = food;
    }
  
    
    

}
