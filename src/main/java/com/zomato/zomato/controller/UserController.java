package com.zomato.zomato.controller;
import org.springframework.web.bind.annotation.RestController;
import com.zomato.zomato.DTO.AddressDTO;
import com.zomato.zomato.DTO.OrderCancellationDTO;
import com.zomato.zomato.DTO.OrderHeaderDTO;
import com.zomato.zomato.DTO.RestaurantTimingDTO;
import com.zomato.zomato.DTO.UserLoginDTO;
import com.zomato.zomato.DTO.updateMenuDTO;
import com.zomato.zomato.DTO.updateUserDTO;
import com.zomato.zomato.DTO.updateRestaurantDTO;
import com.zomato.zomato.Repository.AddMenuRepo;
import com.zomato.zomato.Repository.AddToCartRepo;
import com.zomato.zomato.Repository.CouponRepository;
import com.zomato.zomato.Repository.FavoriteRepository;
import com.zomato.zomato.Repository.OrderCancelRepo;
import com.zomato.zomato.Repository.OrderHeaderRepo;
import com.zomato.zomato.Repository.OrderItemsRepository;
import com.zomato.zomato.Repository.RestaurantBalanceRepo;
import com.zomato.zomato.Repository.UserRepository;
import com.zomato.zomato.Repository.UserWalletRepo;
import com.zomato.zomato.Repository.restaurantRepository;
import com.zomato.zomato.model.RestaurantRegister.RestaurantStatus;
import com.zomato.zomato.service.UserService;
import com.zomato.zomato.service.OrderCancellationsService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import  com.zomato.zomato.model.*;

@RestController

public class UserController {
    
    @Autowired
    private UserRepository repo;

    
    @GetMapping("/allusers")
    public List<UserRegisteration> getAllRegister() {
        return repo.findAll();
    }
    // All people(including customber and owner) can register
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String Register_user(@RequestBody UserRegisteration entity) {
        userService.register(entity);
        return "registered successfully";
        
    }
    @DeleteMapping("/delete/{id}")
    
    public String Delete_Users(@PathVariable int id) {
        repo.deleteById(id);
        return "Deleted Successfully";
    }
    @GetMapping("/login")
    public String LoginVerify(@RequestBody UserLoginDTO entity) {

        return userService.UserLogin(entity);
    }
    @PutMapping("update/{id}")
    public String UpdateUser(@PathVariable int id, @RequestBody updateUserDTO entity) {
        return userService.UpdateUser(id,entity);
    }
    @Autowired
    private restaurantRepository restaurant;
    // RESTAURANT REGISTRATION
    @PostMapping("/restaurantR")
    public RestaurantRegister postMethodName(@RequestBody RestaurantRegister entity) { 
        return restaurant.save(entity);
    }
    @GetMapping("/getRestaurant")
    public List<RestaurantRegister> getMethodName() {
        return restaurant.findAll();
    }
    
    //STATUS = PROGRESS

    @PutMapping("progress/{id}/{status}")
    public String progess_Status(@PathVariable int id,@PathVariable RestaurantStatus status) {
        RestaurantRegister user = restaurant.findById(id).orElse(null);
        if(user == null) return "Restaurant does not exist";
        userService.updateStatus(id, status);
        return "STATUS "+ status;
    }
    @PostMapping("Addmenu/{resid}")
    public String postMethodName(@PathVariable int resid,@RequestBody AddMenu entity) {
        
        return userService.AddItems(resid,entity);
    } 
    @Autowired
    private AddMenuRepo addrepo;
    @GetMapping("getMenu")
    public List<AddMenu> getMenu() {
        return addrepo.findAll() ;
    }
   
    @PutMapping("updateRestuarant/{id}")
    public String Update_Restaurant(@PathVariable int id, @RequestBody updateRestaurantDTO entity) {
        return userService.UpdateRestaurant(id,entity);
    }

    @PutMapping("updatemenu/{id}")
    public String updateMenu(@PathVariable int id, @RequestBody updateMenuDTO entity) {
        return userService.UpdateMenu(id,entity);
    }
    @GetMapping("/searchN/{name}")
    public List<RestaurantRegister> searchByName(@PathVariable String name) {
    return userService.searchByName(name);
    }
    @GetMapping("/searchR/{rating}")
    public List<RestaurantRegister> searchByRating(@PathVariable Double rating) {
        return userService.searchByRating(rating);
    }
    @GetMapping("searchDishes/{dishes}")
    public List<AddMenu> searchDishes(@PathVariable String dishes) {
        return addrepo.findByDishesContainingIgnoreCase(dishes);
    }
    @Autowired
    private AddToCartRepo atcrepo;

    @PostMapping("/cart")
    public String AddToCart(@RequestBody Cart entity) {
        atcrepo.save(entity);
    
        return "ADD To Cart Successfully";
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderHeaderDTO dto) {

        String response = userService.placeOrder(dto);

        return ResponseEntity.ok(response);
    }
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @PutMapping("orderitemDelete/{id}")
    public String OrderItemsDeletion(@PathVariable int id) {
        orderItemsRepository.deleteById(id);
        return "Specific Order Deleted Successfully";
   }
   @Autowired
   private OrderHeaderRepo orderHeaderRepo;
   @PutMapping("orderheaderDelete/{id}")
   public String OrderHeaderDeletion(@PathVariable int id) {
      orderHeaderRepo.deleteById(id);
       return "Order Deleted Successfully";
   }


    @Autowired
    private FavoriteRepository favoriteRepository;

    @PostMapping("fav/{userid}/{restaurantid}")
    public String postMethodName(@PathVariable int userid,@PathVariable int restaurantid) {
       return userService.FavouriteRestaurant(userid,restaurantid);
        
    }
    @PostMapping("/timing")
    public String RestaurantTimingSet(@RequestBody RestaurantTimingDTO entity){
       return userService.RestaurantTiming(entity);
    } 
    @Autowired
    private CouponRepository couponRepository;
    @PostMapping("/coupon")  
    public String CouponAdded(@RequestBody Coupon entity) {
        couponRepository.save(entity);
        return "Coupon Added Successfully";
    } 
    // ORDER CANCELLATIONS
   @Autowired
   private OrderCancellationsService order;
    @PostMapping("/cancelOrder")
    public String postMethodName(@RequestBody  OrderCancellationDTO entity) {
        return order.Ordercancel(entity);
        
    }

    @Autowired
    private UserWalletRepo walletRepo;

    @PostMapping("/wallet")
    public UserWallet Wallet(@RequestBody UserWallet entity) {
        
        return walletRepo.save(entity);
    }
    @Autowired
    private RestaurantBalanceRepo restaurantBalanceRepo;
    @PostMapping("restaurantWallet")
    public RestaurantWallet RestaurantWallet(@RequestBody RestaurantWallet entity) {
        return restaurantBalanceRepo.save(entity);
    }
    
    
    
   
}
