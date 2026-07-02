package com.zomato.zomato.DTO;

import java.time.LocalTime;

import com.zomato.zomato.model.RestaurantRegister;

public class RestaurantTimingDTO {

    private int restaurantId;
    private LocalTime openTime;
    private LocalTime closeTime;

    public LocalTime getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }
    public LocalTime getOpenTime() {
        return openTime;
    }
    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }
    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
