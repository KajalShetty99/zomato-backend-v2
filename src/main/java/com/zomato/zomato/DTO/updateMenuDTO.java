package com.zomato.zomato.DTO;

public class updateMenuDTO {
    private String Dishes;
    private Double price;

    public void setDishes(String dishes) {
        Dishes = dishes;
    }
    public String getDishes() {
        return Dishes;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
