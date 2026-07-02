package com.zomato.zomato.DTO;
import java.util.*;
import com.zomato.zomato.model.*;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class OrderHeaderDTO {

    private int userId;
    private List<OrderItemDTO> items;
    private String deliveryAddress;

   
   private OrderHeader.PaymentMethod paymentMethod;

    // private String paymentMethod;
    private Coupon coupon;

    public Coupon getCoupon() {
       return coupon;
    }
    public void setCoupon(Coupon coupon) {
       this.coupon = coupon;
    } 
    public List<OrderItemDTO> getItems() {
        return items;
    }
    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public OrderHeader.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(OrderHeader.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
   
   

}
