package com.zomato.zomato.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zomato.zomato.model.UserRegisteration;
public interface UserRepository extends JpaRepository<UserRegisteration, Integer>{

    UserRegisteration findByName(String name); 
}
