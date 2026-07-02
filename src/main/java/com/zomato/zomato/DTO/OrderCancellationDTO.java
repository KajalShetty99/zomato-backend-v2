package com.zomato.zomato.DTO;

import com.zomato.zomato.model.OrderItem;
import com.zomato.zomato.model.UserRegisteration;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderCancellationDTO {

    private int orderItemId;
    private int userId;

    private String reason;
    private String refundStatus;

   
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getRefundStatus() {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    public int getOrderItemId() {
        return orderItemId;
    }
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
