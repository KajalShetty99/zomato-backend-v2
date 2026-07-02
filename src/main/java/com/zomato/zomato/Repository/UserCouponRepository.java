package com.zomato.zomato.Repository;
import com.zomato.zomato.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Integer>{
  boolean existsByUserAndCoupon(UserRegisteration user, Coupon coupon);
}