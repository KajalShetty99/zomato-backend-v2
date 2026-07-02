package com.zomato.zomato.model;
import jakarta.persistence.*;

@Entity
@Table(name = "AddMenu")
public class AddMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dishes;
   
    private Double price;

    @ManyToOne //Because one restaurant can have many menu items
    @JoinColumn(name = "restaurant_id")  
    // restaurant_id is the foreign key pointing to RestaurantRegister
    private RestaurantRegister restaurant;

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }
    public String getDishes() {
        return dishes;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() {
        return price;
    }
    public void setRestaurant(RestaurantRegister restaurant) {
        this.restaurant = restaurant;
    }
    public RestaurantRegister getRestaurant() {
        return restaurant;
    }
    

}
 