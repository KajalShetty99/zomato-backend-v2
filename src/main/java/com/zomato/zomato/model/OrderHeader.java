package com.zomato.zomato.model;
import java.time.LocalDateTime;
import com.zomato.zomato.model.*;
import com.zomato.zomato.model.OrderHeader.PaymentMethod;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.*;
import com.zomato.zomato.model.RestaurantRegister.RestaurantStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "order_header")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalAmount;
    private String deliveryAddress;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegisteration user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantRegister Restaurant;

    @OneToMany(mappedBy = "order")   
    private List<OrderItem> orderItems;
    
    @Enumerated(EnumType.STRING)
    private orderStatus status = orderStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = null;
    
     
    @Enumerated(EnumType.STRING)
    private PaymentStatus done_notdone = PaymentStatus.UNPAID;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum orderStatus {
       
        PENDING,
        ACCEPTED, 
        PREPARING,
        CANCELLED,
        DELIVERED,
        REJECTED
    }
        
    public enum PaymentMethod {
       
        CASH,
        UPI ,
        WALLET
    }

    public enum PaymentStatus{
        UNPAID,
        PAID
        
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public PaymentStatus getDone_notdone() {
        return done_notdone;
    }
    public void setDone_notdone(PaymentStatus done_notdone) {
        this.done_notdone = done_notdone;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public orderStatus getStatus() {
        return status;
    }
    public void setStatus(orderStatus status) {
        this.status = status;
    }
    public double getTotalAmount() {
        return totalAmount;
    }public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public UserRegisteration getUser() {
        return user;
    }
    public void setUser(UserRegisteration user) {
        this.user = user;
    }
    public RestaurantRegister getRestaurant() {
        return Restaurant;
    }
    public void setRestaurant(RestaurantRegister restaurant) {
        Restaurant = restaurant;
    }
    
}
