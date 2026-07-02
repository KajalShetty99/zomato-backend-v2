package com.zomato.zomato.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "RestaurantWalletTransaction")
public class RestaurantWalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantRegister restaurant;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderHeader order;

    // private double amount;  // fetch through order...order.TotalBalance();

    private LocalDateTime createdAt;
    // private OrderHeader.PaymentMethod paymentMethod; 
    //then JPA stores the enum as its ordinal (0, 1, 2) by default.
    @Enumerated(EnumType.STRING)
    private OrderHeader.PaymentMethod paymentMethod;
    // public double getAmount() {
    //     return amount;
    // }
    // public void setAmount(double amount) {
    //     this.amount = amount;
    // }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OrderHeader getOrder() {
        return order;
    }
    public void setOrder(OrderHeader order) {
        this.order = order;
    }
    public OrderHeader.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(OrderHeader.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public RestaurantRegister getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(RestaurantRegister restaurant) {
        this.restaurant = restaurant;
    }

    
}
