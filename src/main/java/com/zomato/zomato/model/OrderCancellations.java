package com.zomato.zomato.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "OrderCancellation")
public class OrderCancellations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne  //it means many cancelletaions can be done to a single items
    @JoinColumn(name = "orderitem_id")
    private OrderItem item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRegisteration user;

    private LocalDateTime cancelledAt;

    private String reason;

    private String refundStatus;

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OrderItem getItem() {
        return item;
    }
    public void setItem(OrderItem item) {
        this.item = item;
    }
    // public CancellationReason getReason() {
    //     return reason;
    // }
    // public void setReason(CancellationReason reason) {
    //     this.reason = reason;
    // }
    // public RefundStatus getRefundStatus() {
    //     return refundStatus;
    // }
    // public void setRefundStatus(RefundStatus refundStatus) {
    //     this.refundStatus = refundStatus;
    // }
    public UserRegisteration getUser() {
        return user;
    }
    public void setUser(UserRegisteration user) {
        this.user = user;
    }
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
}
