package com.zomato.zomato.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import com.zomato.zomato.DTO.OrderCancellationDTO;
import com.zomato.zomato.Repository.OrderCancelRepo;
import com.zomato.zomato.Repository.OrderHeaderRepo;
import com.zomato.zomato.Repository.OrderItemsRepository;
import com.zomato.zomato.Repository.RestaurantBalanceRepo;
import com.zomato.zomato.Repository.UserRepository;
import com.zomato.zomato.Repository.UserWalletRepo;
import com.zomato.zomato.model.*;
import com.zomato.zomato.model.OrderHeader.PaymentMethod;
import com.zomato.zomato.model.OrderHeader.orderStatus;

@Service
public class OrderCancellationsService{
    
    @Autowired
    private OrderItemsRepository orderitemrepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderHeaderRepo orderHeaderRepo;
    @Autowired
    private UserWalletRepo walletRepo;
    @Autowired
    private OrderCancelRepo orderCancelRepo;
    @Autowired
    private RestaurantBalanceRepo restaurantBalanceRepo;
    
    

    public String Ordercancel(OrderCancellationDTO dto){

        OrderItem orderItem = orderitemrepo.findById(dto.getOrderItemId()).orElse(null);
        if(orderItem == null) return "order Item not Found";

        UserRegisteration user = userRepo.findById(dto.getUserId()).orElse(null);
        if(user == null) return "User not Found";

           OrderCancellations cancel = new OrderCancellations();
           cancel.setCancelledAt(LocalDateTime.now());
           cancel.setUser(user);
           cancel.setReason(dto.getReason());
           cancel.setItem(orderItem);  

        OrderHeader order = orderItem.getOrder();
        RestaurantRegister restaurant = order.getRestaurant();
        RestaurantWallet restaurantWallet = restaurantBalanceRepo.findByRestaurant(restaurant);
        if(order.getPaymentMethod() != PaymentMethod.CASH){
            restaurantWallet.setBalance(restaurantWallet.getBalance() - orderItem.getPrice());
            System.out.println("Current Balance = " + restaurantWallet.getBalance());
            System.out.println("Order Item Price = " + orderItem.getPrice());
            System.out.println("New Balance = " + (restaurantWallet.getBalance() - orderItem.getPrice()));
            restaurantBalanceRepo.save(restaurantWallet);

        }
        if (order.getStatus() == orderStatus.DELIVERED) {
            cancel.setRefundStatus("NOT_APPLICABLE");
            orderCancelRepo.save(cancel);
           
            return "Order cannot be cancelled.";
        }
        if (order.getStatus() == orderStatus.PREPARING) {
            cancel.setRefundStatus("NOT_APPLICABLE");
            orderCancelRepo.save(cancel);
            return "Order cannot be cancelled.";
        }
        if (order.getStatus() == orderStatus.PENDING) {
            if(order.getPaymentMethod() == PaymentMethod.UPI){
                cancel.setRefundStatus("PROCESSED");
                   orderCancelRepo.save(cancel);
                    return "Money Transfer SuccessFully";
            }
            else if(order.getPaymentMethod() == PaymentMethod.WALLET){
                   UserWallet userWallet = walletRepo.findByUser(user);
                   userWallet.setBalance(userWallet.getBalance() + orderItem.getPrice());
                   walletRepo.save(userWallet);
                   cancel.setRefundStatus("PROCESSED");

                   orderCancelRepo.save(cancel);
            }
            
        }
       return "Order cancelled.";    
    }
}

