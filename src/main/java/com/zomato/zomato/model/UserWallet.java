package com.zomato.zomato.model;
import com.zomato.zomato.model.UserRegisteration;
import jakarta.persistence.*;
@Entity
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserRegisteration user;

    private Double balance;
    
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public UserRegisteration getUser() {
        return user;
    }
    public void setUser(UserRegisteration user) {
        this.user = user;
    }
}
