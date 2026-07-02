package com.zomato.zomato.model;
import jakarta.persistence.*;

@Entity
@Table(name = "orderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "order_id")  
    private OrderHeader order;

    @ManyToOne
    @JoinColumn(name = "menu_id")  //menu ke saath restaurant kaa b information
    private AddMenu menu;

    private int quantity;
    private double price;

    public AddMenu getMenu() {
        return menu;
    }
    public void setMenu(AddMenu menu) {
        this.menu = menu;
    }

    public OrderHeader getOrder() {
        return order;
    }
    public void setOrder(OrderHeader order) {
        this.order = order;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}






