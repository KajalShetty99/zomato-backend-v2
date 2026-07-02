package com.zomato.zomato.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zomato.zomato.model.UserRegisteration;
import com.zomato.zomato.model.UserWallet;
import com.zomato.zomato.model.UserWallet;
import java.util.Optional;

public interface UserWalletRepo extends JpaRepository<UserWallet, Integer>{
    UserWallet findByUser(UserRegisteration user);
}

