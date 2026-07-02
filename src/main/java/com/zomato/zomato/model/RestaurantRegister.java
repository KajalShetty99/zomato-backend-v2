package com.zomato.zomato.model;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "RestaurantRegister")
public class RestaurantRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String restaurantName;
    private String ownerName;
    private String email;
    private String phone;
    private String gstNumber;
    private Double rating;
    private LocalTime openTime;
    private LocalTime closeTime;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status = RestaurantStatus.PENDING;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }
    public String getGstNumber() {
        return gstNumber;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setStatus(RestaurantStatus status) {
        this.status = status;
    }
    public RestaurantStatus getStatus() {
        return status;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        rating = rating;
    }

    public enum RestaurantStatus {
        PENDING,
        VERIFIED,
        REJECTED
    }
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
    
    
}

