package com.zomato.zomato.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import com.zomato.zomato.DTO.UserLoginDTO;
import com.zomato.zomato.DTO.OrderHeaderDTO;
import com.zomato.zomato.DTO.OrderItemDTO;
import com.zomato.zomato.DTO.RestaurantTimingDTO;
import com.zomato.zomato.DTO.updateMenuDTO;
import com.zomato.zomato.DTO.updateRestaurantDTO;
import com.zomato.zomato.DTO.updateUserDTO;
import com.zomato.zomato.Repository.*;
import com.zomato.zomato.config.*;
import com.zomato.zomato.model.*;
import com.zomato.zomato.model.OrderHeader.orderStatus;
import com.zomato.zomato.model.RestaurantRegister.RestaurantStatus;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

      // USER REGISTRATION
    public UserRegisteration register(UserRegisteration user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());        
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
    public String UserLogin(UserLoginDTO user){
        UserRegisteration u = userRepository.findByName(user.getName());
        if(u == null) return "User does not exist";
        boolean isPasswordCorrect = passwordEncoder.matches(user.getPassword(),u.getPassword());                
        if (!isPasswordCorrect) {
            return "Invalid Password";
        }
        return "Login Successful";
    
    }
    public String UpdateUser(int id,updateUserDTO dto){
        UserRegisteration user =  userRepository.findById(id).orElse(null);
        if(user == null) return "User Not Found";

        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
        if(dto.getName() != null) user.setName(dto.getName());
        if(dto.getPassword() != null) user.setPassword(dto.getPassword());
        if(dto.getPhoneNo() != null) user.setPhoneNo(dto.getPhoneNo());
         
        userRepository.save(user);

       return "User Updated Successfully";
    }
    @Autowired
    private restaurantRepository resrepo;

    public void updateStatus(int id, RestaurantStatus status) {
    RestaurantRegister restaurant = resrepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurant not found"));

    restaurant.setStatus(status);
    resrepo.save(restaurant);
    }
     
    @Autowired 
    private AddMenuRepo addrepo;  
    public String AddItems(int id,AddMenu entity){
        RestaurantRegister user = resrepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        
            if(user.getStatus() ==  RestaurantStatus.PENDING || user.getStatus() ==  RestaurantStatus.REJECTED){
               return "Restaurant Verification fails";
            } 
            if(user.getStatus() ==  RestaurantStatus.VERIFIED){
                entity.setRestaurant(user);
                addrepo.save(entity);
            }
       return "Added Menu Successfully";
    }

    public String UpdateRestaurant(int id,updateRestaurantDTO dto){
        RestaurantRegister user = resrepo.findById(id).orElse(null);
        if(user == null) return "Restaurant Does not exist";

        if(dto.getEmail() != null) user.setEmail(dto.getEmail());
        if(dto.getOwnerName() != null) user.setOwnerName(dto.getOwnerName());
        if(dto.getGstNumber() != null) user.setGstNumber(dto.getGstNumber());
        if(dto.getPhone() != null) user.setPhone(dto.getPhone());
        if(dto.getRestaurantName() != null) user.setRestaurantName(dto.getRestaurantName());

        resrepo.save(user);
        return "Updated Successfully";

    }
   
    public String UpdateMenu(int id,updateMenuDTO dto){
        AddMenu user = addrepo.findById(id).orElse(null);
        if(user == null) return "Restaurant Does not exist";
        
        if(dto.getDishes() != null) user.setDishes(dto.getDishes());
        if(dto.getPrice() != null) user.setPrice(dto.getPrice());
        addrepo.save(user);
        return "Updated SuccessFully ";
    }

    public List<RestaurantRegister> searchByName(String name) {
    return resrepo.findByRestaurantNameContainingIgnoreCase(name);
}
 public List<RestaurantRegister> searchByRating(Double rating) {
     return resrepo.findByRatingGreaterThanEqual(rating);
    // return resrepo.findByRating(Rating);
}

@Autowired 
private OrderItemsRepository orderItemRepo;

@Autowired
private OrderHeaderRepo orderHeaderRepo;
@Autowired
private CouponRepository couponRepo;
@Autowired
private UserCouponRepository ucouponRepo;
@Autowired
private RestaurantTransactionRepo transactionRepo;
@Autowired
private RestaurantBalanceRepo  restaurantBalanceRepo;

public String placeOrder(OrderHeaderDTO dto) {

    
    UserRegisteration user = userRepository.findById(dto.getUserId()).orElse(null);
        if(user == null) return "User does not exist";

     // Step 2: Get first menu to find restaurant
    OrderItemDTO firstItem = dto.getItems().get(0);   // items is of list type
    AddMenu firstMenu = addrepo.findById(firstItem.getMenuId())
            .orElseThrow(() -> new RuntimeException("Menu not found"));

    RestaurantRegister restaurant = firstMenu.getRestaurant();
    if(restaurant.getStatus() != RestaurantStatus.VERIFIED) return "Restaurant not Verified or Pending";
    

    LocalTime orderTime = LocalDateTime.now().toLocalTime();
    if (orderTime.isBefore(restaurant.getOpenTime()) || orderTime.isAfter(restaurant.getCloseTime())) {
        return "Restaurant is closed";
    }
    //COUPON CHECKING -------------------------------------------------------------------------------
     if(dto.getCoupon() != null){
       
        Coupon c =  couponRepo.findByCode(dto.getCoupon().getCode())
        .orElseThrow(() -> new RuntimeException("Coupon not found"));

        boolean alreadyUsed = ucouponRepo.existsByUserAndCoupon(user, c);

        if (alreadyUsed) {
        return ("Coupon already used by this user");
        }
    }
    // Step 3: Create OrderHeader
    OrderHeader header = new OrderHeader();
    header.setUser(user);
    header.setRestaurant(restaurant);
    header.setCreatedAt(LocalDateTime.now());
    header.setStatus(orderStatus.PENDING);
    header.setDeliveryAddress(dto.getDeliveryAddress());
    header.setPaymentMethod(dto.getPaymentMethod());
    // Save header first (IMPORTANT → generates orderId)
    header = orderHeaderRepo.save(header);

    // setting Restaurant Transactions

    RestaurantWalletTransaction walletTransaction = new RestaurantWalletTransaction();
    walletTransaction.setOrder(header);
    walletTransaction.setCreatedAt(LocalDateTime.now());
    walletTransaction.setRestaurant(restaurant);
    walletTransaction.setPaymentMethod(dto.getPaymentMethod());

    RestaurantWallet wallet = restaurantBalanceRepo
        .findByRestaurant(header.getRestaurant());

        

    if (wallet == null) {
        wallet = new RestaurantWallet();
        wallet.setRestaurant(header.getRestaurant());
        // wallet.setBalance(0.0);
    }
        double totalAmount = 0;
     // Step 4: Create OrderItems
    for (OrderItemDTO itemDTO : dto.getItems()) {

        AddMenu menu = addrepo.findById(itemDTO.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        OrderItem item = new OrderItem();
        item.setOrder(header);
        item.setMenu(menu);
        item.setQuantity(itemDTO.getQuantity());

        double price = menu.getPrice() * itemDTO.getQuantity();
        item.setPrice(price);

        totalAmount += price;

        orderItemRepo.save(item);
    }
     if(dto.getCoupon() != null){
       
        Coupon c =  couponRepo.findByCode(dto.getCoupon().getCode())
        .orElseThrow(() -> new RuntimeException("Coupon not found"));

        double discount = totalAmount * (c.getDiscount() / 100.0);
        double finalAmount = totalAmount - discount;
        header.setTotalAmount(finalAmount);

        wallet.setBalance(wallet.getBalance()+finalAmount);

        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCoupon(c); //couponid save huaa;
        userCoupon.setUsedAt(LocalDate.now());
        userCoupon.setUser(user);
        ucouponRepo.save(userCoupon);
    }
    else{
        wallet.setBalance(wallet.getBalance()+totalAmount);
        header.setTotalAmount(totalAmount);
    }
    transactionRepo.save(walletTransaction);
    restaurantBalanceRepo.save(wallet);
    orderHeaderRepo.save(header);

    return "Order placed successfully with ID: " + header.getId();
}

@Autowired
private FavoriteRepository   favoriteRepository;

public String FavouriteRestaurant(int userid , int restaurantid){
    UserRegisteration user = userRepository.findById(userid).orElse(null);
        if(user == null) return "User does not exist";
    RestaurantRegister restaurant = resrepo.findById(restaurantid).orElse(null);
        if(restaurant == null) return "Restaurant Does not exist";
    if (restaurant.getStatus() != RestaurantStatus.VERIFIED) {
        throw new RuntimeException("Only verified restaurants can be added to favorites.");
    }
    FavoriteRestaurant favRest = new FavoriteRestaurant();
         favRest.setUser(user);
         favRest.setRestaurant(restaurant);

         favoriteRepository.save(favRest);
         return "userid "+ userid + " has favourite Restaurant " + restaurantid;

}
    public String RestaurantTiming(RestaurantTimingDTO dto){
        RestaurantRegister restaurant = resrepo.findById(dto.getRestaurantId()).orElse(null);
        restaurant.setCloseTime(dto.getCloseTime());
        restaurant.setOpenTime(dto.getOpenTime());
        resrepo.save(restaurant);
        return "Timing updated Successfully";
    }

}

