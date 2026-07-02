package com.zomato.zomato.model;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "UserCoupon")
public class UserCoupon {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private UserRegisteration user;

   private LocalDate usedAt;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

   public Coupon getCoupon() {
       return coupon;
   }
   public void setCoupon(Coupon coupon) {
       this.coupon = coupon;
   }
   public int getId() {
       return id;
   }
   public void setId(int id) {
       this.id = id;
   }
   public LocalDate getUsedAt() {
       return usedAt;
   }
   public void setUsedAt(LocalDate usedAt) {
       this.usedAt = usedAt;
   }
   public UserRegisteration getUser() {
       return user;
   }
   public void setUser(UserRegisteration user) {
       this.user = user;
   }
   
}
