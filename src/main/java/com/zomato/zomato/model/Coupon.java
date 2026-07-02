package com.zomato.zomato.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String active;
    private LocalDate expiryDate; 
    private int minimumOrder;
    private int discount;
    
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getMinimumOrder() {
        return minimumOrder;
    }
    public void setMinimumOrder(int minimumOrder) {
       this.minimumOrder = minimumOrder;
    }
    
}
